// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 及 WebGL编程指南和源代码/ch05/ColoredTriangle.js
// Vertex shader program
var VSHADER_SOURCE =
    'attribute vec4 a_Position;\n' +
    'attribute vec4 a_Color;\n' +
    'varying vec4 v_Color;\n' +
    'uniform mat4 u_ModelMatrix;\n' +
    'void main() {\n' +
    '  gl_Position = u_ModelMatrix * a_Position;\n' +
    '  v_Color = a_Color;\n' +
    '}\n';

// 参考 WebGL编程指南和源代码/ch05/ColoredTriangle.js
// Fragment shader program
var FSHADER_SOURCE =
    'precision mediump float;\n' +
    'varying vec4 v_Color;\n' +
    'void main() {\n' +
    '  gl_FragColor = v_Color;\n' +
    '}\n';

// 当前旋转角度（默认为0）
let currentAngle = 0.0;
// 当前缩放比例（默认为1）
let currentScale = 1.0;

// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 及 WebGL编程指南和源代码/ch05/ColoredTriangle.js
// 及 WebGL编程指南和源代码/ch07/LookAtTrianglesWithKeys.js
function main() {
    // 获取Html元素canvas，设置长宽
    // Retrieve <canvas> element
    let canvas = document.getElementById('webgl');
    canvas.setAttribute("width", canvasSize.maxX);
    canvas.setAttribute("height", canvasSize.maxY);

    // 调换polygon数组中，四边形第一和第二个顶点的index的顺序，因为polygon数组中以y轴坐标最小的顶点为起始点顺时针
    // 排列四个顶点在vertex_pos等数组中的下标，而WebGL的顶点着色器应按x轴坐标从小到大的顺序读取顶点信息
    for (let i = 0; i < polygon.length; i++) {
        let temp = polygon[i][0];
        polygon[i][0] = polygon[i][1];
        polygon[i][1] = temp;
    }

    // 获取WebGL的上下文
    // Get the rendering context for WebGL
    let gl = getWebGLContext(canvas);
    if (!gl) {
        console.log('Failed to get the rendering context for WebGL');
        return;
    }

    // 编译、连接和启用Shader
    // Initialize shaders
    if (!initShaders(gl, VSHADER_SOURCE, FSHADER_SOURCE)) {
        console.log('Failed to initialize shaders.');
        return;
    }

    // 将顶点的位置写入顶点着色器
    // Write the positions of vertices to a vertex shader
    let n = initVertexBuffers(gl);
    if (n < 0) {
        console.log('Failed to set the vertex information');
        return;
    }

    // 指定canvas的背景颜色为黑色
    // Specify the color for clearing <canvas>
    gl.clearColor(0.0, 0.0, 0.0, 1.0);

    // 获取变换矩阵
    // Pass the model matrix to the vertex shader
    let u_ModelMatrix = gl.getUniformLocation(gl.program, 'u_ModelMatrix');
    if (!u_ModelMatrix) {
        console.log('Failed to get the storage location of u_ModelMatrix');
        return;
    }

    // 设置键盘按下事件（T/B/E键，分别对应启动动画状态（或暂停动画状态）、隐藏网格边框（或显示网格边框）、进入编辑状态）
    // Register the event handler to be called on key press
    document.onkeydown = function (ev) {
        keydown(ev, gl, u_ModelMatrix);
    };

    // 设置鼠标按下事件（在编辑状态下，选择四边形顶点并按下后可以拖动顶点位置）
    document.onmousedown = function (ev) {
        mousedown(ev, gl, u_ModelMatrix);
    };

    // 设置鼠标松开事件（在编辑状态下，与鼠标按下事件对应）
    document.onmouseup = function (ev) {
        mouseup(ev, gl, u_ModelMatrix);
    };

    // 绘制初始状态下的图形（默认旋转角度、默认缩放比例）
    draw(gl, currentAngle, currentScale, u_ModelMatrix);
}

// 当前是否在动画状态下（即是否在旋转）（默认不在动画状态下）(若不在动画状态下，即在编辑状态下)
let isRotating = false;
// 当前是否显示网格边框（默认需要显示网格边框）
let isLineShown = true;
// 参考 WebGL编程指南和源代码/ch07/LookAtTrianglesWithKeys.js
// 键盘按下事件
function keydown(ev, gl, u_ModelMatrix) {
    if (ev.keyCode === 84) {  // 按下T键，启动动画状态（或暂停动画状态）
        if (isRotating) {  // 动画状态下按下T键，暂停动画状态
            pauseAnimationState();
        } else {  // 非动画状态下按下T键，启动动画状态
            startAnimationState(gl, u_ModelMatrix);
        }
    } else if (ev.keyCode === 66) {  // 按下B键，隐藏网格边框（或显示网格边框）
        // 更新是否显示网格边框
        isLineShown = !isLineShown;
        // 重新绘制图形
        draw(gl, currentAngle, currentScale, u_ModelMatrix);
    } else if (ev.keyCode === 69) {  // 按下E键，进入编辑状态
        if (isRotating) {  // 动画状态下按下E键，暂停动画状态
            pauseAnimationState();
        }
        // 重新设置旋转角度为0，缩放比例为1
        currentAngle = 0.0;
        currentScale = 1.0;
        // 重新绘制图形
        draw(gl, currentAngle, currentScale, u_ModelMatrix);
    }
}

// 回调函数ID
let animationID = null;
// 暂停动画状态
function pauseAnimationState() {
    // 暂停动画（即取消每帧刷新的事件）
    window.cancelAnimationFrame(animationID);
    // 设置为不在动画状态下
    isRotating = false;
}

// Last time that this function was called
let g_last = Date.now();
// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 启动动画状态
function startAnimationState(gl, u_ModelMatrix) {
    // Last time that this function was called
    g_last = Date.now();

    // Start drawing
    // 设置回调函数（每一帧都会调用）
    let tick = function () {
        // 更新旋转角度与缩放比例
        animate();
        // Draw the triangle
        draw(gl, currentAngle, currentScale, u_ModelMatrix);
        // Request that the browser ?calls tick
        animationID = requestAnimationFrame(tick);
    };
    tick();
    isRotating = true;//设置旋转状态
}

// 动画状态下是否在增大（默认为缩小）
let isExpanding = false;
// 每秒旋转的角度为45°
const ANGLE_STEP = 45.0;
// 每秒缩放的比例为0.2
const SCALE_STEP = 0.2;
// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 更新旋转角度与缩放比例
function animate() {
    // 计算经过的时间
    // Calculate the elapsed time
    let now = Date.now();
    let elapsed = now - g_last;
    g_last = now;

    // 更新当前旋转角度
    // Update the current rotation angle (adjusted by the elapsed time)
    currentAngle = (currentAngle + (ANGLE_STEP * elapsed) / 1000.0) % 360;

    // 计算缩放的比例数值
    let scale = SCALE_STEP * elapsed / 1000;

    // 更新当前缩放比例
    if (isExpanding) {  // 当前正在增大
        if (currentScale + scale < 1) {  // 没有增大到原图形大小
            currentScale += scale;
        } else {  // 增大到了原图形大小，将转入缩小
            currentScale = 2 - currentScale - scale;
            isExpanding = false;
        }
    } else {  //当前正在缩小
        if (currentScale - scale > 0.2) {  // 没有缩小到原图形的20%
            currentScale -= scale;
        } else {  // 缩小到了原图形的20%，将转入增大
            currentScale = 0.4 - currentScale + scale;
            isExpanding = true;
        }
    }
}

// 顶点颜色
let verticesColors = new Float32Array(polygon.length);
// canvas最大宽度的一半
const halfCanvasWidth = canvasSize.maxX / 2;
// canvas最大高度的一半
const halfCanvasHeight = canvasSize.maxY / 2;
// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 初始化顶点buffer
function initVertexBuffers(gl) {
    // 各顶点对应的颜色信息
    let tempVerticesColors = [];
    // The number of vertices
    let n = 4;
    let count = 0;

    // 在tempVerticesColors中存储顶点在canvas坐标系下的坐标（因此需要对原始坐标进行变换）及颜色，用于绘制四边形
    for (let i = 0; i < polygon.length; i++) {
        let quad = polygon[i];
        for (let j = 0; j < polygon[i].length; j++) {
            let point = quad[j];
            tempVerticesColors[count++] = (vertex_pos[point][0] - halfCanvasWidth) / halfCanvasWidth;
            tempVerticesColors[count++] = (halfCanvasHeight - vertex_pos[point][1]) / halfCanvasHeight;
            tempVerticesColors[count++] = vertex_color[point][0] / 255;
            tempVerticesColors[count++] = vertex_color[point][1] / 255;
            tempVerticesColors[count++] = vertex_color[point][2] / 255;
        }
    }

    // 在tempVerticesColors中存储顶点在canvas坐标系下的坐标（因此需要对原始坐标进行变换）及颜色，用于绘制网格边框
    for (let i = 0; i < polygon.length; i++) {
        let quad = polygon[i];
        for (let j = 0; j < polygon[i].length; j++) {
            let point = quad[j];
            tempVerticesColors[count++] = (vertex_pos[point][0] - halfCanvasWidth) / halfCanvasWidth;
            tempVerticesColors[count++] = (halfCanvasHeight - vertex_pos[point][1]) / halfCanvasHeight;
            tempVerticesColors[count++] = 1.0;
            tempVerticesColors[count++] = 0.0;
            tempVerticesColors[count++] = 0.0;
        }
    }

    // 将顶点相关信息存储为Float32Array
    verticesColors = new Float32Array(tempVerticesColors);

    // Create a buffer object
    let vertexColorBuffer = gl.createBuffer();
    if (!vertexColorBuffer) {
        console.log('Failed to create the buffer object');
        return false;
    }

    // Bind the buffer object to target
    gl.bindBuffer(gl.ARRAY_BUFFER, vertexColorBuffer);
    // Write date into the buffer object
    gl.bufferData(gl.ARRAY_BUFFER, verticesColors, gl.DYNAMIC_DRAW);

    // Assign the buffer object to a_Position variable
    let a_Position = gl.getAttribLocation(gl.program, 'a_Position');
    if (a_Position < 0) {
        console.log('Failed to get the storage location of a_Position');
        return -1;
    }
    gl.vertexAttribPointer(a_Position, 2, gl.FLOAT, false, verticesColors.BYTES_PER_ELEMENT * 5, 0);

    // Enable the assignment of the buffer object
    gl.enableVertexAttribArray(a_Position);

    // Assign the buffer object to a_Position variable
    let a_Color = gl.getAttribLocation(gl.program, 'a_Color');
    if (a_Color < 0) {
        console.log('Failed to get the storage location of a_Color');
        return -1;
    }
    gl.vertexAttribPointer(a_Color, 3, gl.FLOAT, false, verticesColors.BYTES_PER_ELEMENT * 5, verticesColors.BYTES_PER_ELEMENT * 2);

    // Enable the assignment of the buffer object
    gl.enableVertexAttribArray(a_Color);

    return n;
}

// 变换矩阵
let modelMatrix = new Matrix4();
// 参考 WebGL编程指南和源代码/ch04/RotatedTranslatedTriangle.js
// 绘制图形函数
function draw(gl, currentAngle, scale, u_ModelMatrix) {
    // 设置旋转矩阵
    modelMatrix.setRotate(currentAngle, 0, 0, 1);
    // 设置缩放矩阵
    modelMatrix.scale(scale, scale, scale);

    // 将旋转、缩放矩阵传递给顶点着色器
    // Pass the rotation matrix to the vertex shader
    gl.uniformMatrix4fv(u_ModelMatrix, false, modelMatrix.elements);

    // 清除当前canvas
    // Clear <canvas>
    gl.clear(gl.COLOR_BUFFER_BIT);

    // 绘制多边形
    // Draw the rectangle
    for (let i = 0; i < 4; i++) {
        gl.drawArrays(gl.TRIANGLE_STRIP, i * 4, 4);
    }

    // 若需要绘制网格边框，则进行绘制
    if (isLineShown) {
        for (let i = 0; i < 4; i++) {
            gl.drawArrays(gl.LINE_LOOP, 16 + i * 4, 3);
            gl.drawArrays(gl.LINE_LOOP, 17 + i * 4, 3);
        }
    }
}

// 参考Project1
// 判断鼠标是否在某一顶点处圆形手柄的范围内（x、y分别为鼠标当前的横纵坐标，vertices为各个顶点坐标构成的二维数组）
function isInCircle(x, y, vertices) {
    for (let i = 0; i < vertices.length; i++) {
        //圆形手柄半径设置为10像素
        let radius = 10;

        //鼠标在某一顶点处圆形手柄的范围内，返回该顶点在vertices数组中的下标
        if (Math.sqrt(Math.pow(x - vertices[i][0], 2) + Math.pow(y - vertices[i][1], 2)) < radius) {
            return i;
        }
    }

    //鼠标不在某一顶点处圆形手柄的范围内，返回-1
    return -1;
}

// 标记当前使用顶点在各顶点坐标数组vertex_pos中的下标
let pointNum = -1;
// 参考Project1
// 鼠标按下事件
function mousedown(ev, gl, u_ModelMatrix) {
    // 只有在编辑状态才可以编辑网格边框（即非动画状态）
    if (!isRotating) {
        let mouseEvent = window.event || ev;
        let x = mouseEvent.offsetX;
        let y = mouseEvent.offsetY;

        // 若按下时鼠标位置在canvas的范围内，且在某一顶点处圆形手柄的范围内，启动鼠标移动事件
        pointNum = isInCircle(x, y, vertex_pos);
        if (pointNum !== -1 && isInCanvas(x, y)) {
            document.onmousemove = function (ev) {
                mousemove(ev, gl, u_ModelMatrix);
            }
        }
    }
}

// 参考Project1
// 鼠标移动事件
function mousemove(ev, gl, u_ModelMatrix) {
    let mouseEvent = window.event || ev;
    let x = mouseEvent.offsetX;
    let y = mouseEvent.offsetY;

    // 若按下时鼠标位置在canvas的范围内，实时更新canvas及各顶点坐标数组vertex_pos
    if (isInCanvas(x, y) && pointNum !== -1) {
        vertex_pos[pointNum][0] = x;
        vertex_pos[pointNum][1] = y;
        let size = Float32Array.BYTES_PER_ELEMENT;

        // 更新buffer中的顶点坐标
        for (let i = 0; i < polygon.length; i++) {
            let tempIndex = i * polygon[i].length * 5;

            for (let j = 0; j < polygon[i].length; j++) {
                if (polygon[i][j] === pointNum) {
                    let tempBuff = new Float32Array([(vertex_pos[pointNum][0] - halfCanvasWidth) / halfCanvasWidth, (halfCanvasHeight - vertex_pos[pointNum][1]) / halfCanvasHeight]);
                    gl.bufferSubData(gl.ARRAY_BUFFER, (tempIndex + j * 5) * size, tempBuff);
                    gl.bufferSubData(gl.ARRAY_BUFFER, (polygon.length * polygon[i].length * 5 + tempIndex + j * 5) * size, tempBuff);
                }
            }
        }

        // 重新绘制图形
        draw(gl, currentAngle, currentScale, u_ModelMatrix);
    }
}

// 参考Project1
// 鼠标松开事件，解除鼠标移动事件
function mouseup(ev, gl, u_ModelMatrix) {
    // 只有在编辑状态才可以编辑网格边框（即非动画状态）
    if (!isRotating) {
        mousemove(ev, gl, u_ModelMatrix);
        document.onmousemove = null;
    }
}

// 参考Project1
// 判断鼠标是否在canvas的范围内（x、y分别为鼠标当前的横纵坐标）
function isInCanvas(x, y) {
    return 0 < x && x < canvasSize.maxX && 0 < y && y < canvasSize.maxY;
}
