// 载入纹理所使用的顶点着色器
let TEXTURE_VSHADER_SOURCE =
    'attribute vec4 a_Position;\n' +
    'attribute vec2 a_TexCoord;\n' +
    'uniform mat4 u_MvpMatrix;\n' +
    'varying vec2 v_TexCoord;\n' +
    'uniform vec4 u_Eye;\n' +
    'varying float v_Dist;\n' +
    'attribute vec4 a_Normal;\n' +            // Normal
    'uniform mat4 u_NormalMatrix;\n' +        // Transformation matrix of the normal
    'uniform mat4 u_ModelMatrix;\n' +         // Model matrix
    'uniform vec3 u_LightColor;\n' +          // Light color (点光源颜色)
    'uniform vec3 u_LightPosition;\n' +       // Position of the light source (in the world coordinate system) (点光源位置)
    'uniform vec3 u_LightDirection;\n' +      // Diffuse light direction (in the world coordinate, normalized) (平行光方向)
    'uniform vec3 u_AmbientLight;\n' +        // Color of an ambient light (环境光颜色)
    'varying float nDotLightDirection;\n' +
    'varying float nDotLightPoint;\n' +
    'varying vec3 v_lightPointColor;\n' +
    'varying vec3 v_lightAmbientColor;\n' +
    'void main() {\n' +
    '  gl_Position = u_MvpMatrix * a_Position;\n' +
    '  v_TexCoord = a_TexCoord;\n' +
       // Recalculate the normal based on the model matrix and make its length 1.
    '  vec3 normal = normalize(vec3(u_NormalMatrix * a_Normal));\n' +
       // The dot product of the light direction and the normal
    '  nDotLightDirection = max(dot(normal, u_LightDirection), 0.0);\n' +
       // Calculate world coordinate of vertex (计算点光源)
    '  vec4 vertexPosition = u_ModelMatrix * a_Position;\n' +
       // Calculate the light direction and make it 1.0 in length
    '  vec3 lightDirection = normalize(u_LightPosition - vec3(vertexPosition));\n' +
       // The dot product of the light direction and the normal
    '  nDotLightPoint = max(dot(lightDirection, normal), 0.0);\n' +
    '  v_lightPointColor = u_LightColor;\n' +
       // Calculate the color due to ambient reflection (计算环境光)
    '  v_lightAmbientColor = u_AmbientLight;\n' +
    '  v_Dist = distance(vertexPosition, u_Eye);\n' +
    '}\n';

// 载入纹理所使用的片段着色器
let TEXTURE_FSHADER_SOURCE =
    '#ifdef GL_ES\n' +
    'precision mediump float;\n' +
    '#endif\n' +
    'uniform sampler2D u_Sampler;\n' +
    'varying vec2 v_TexCoord;\n' +
    'varying float nDotLightDirection;\n' +      //光源参数
    'varying float nDotLightPoint;\n' +
    'varying vec3 v_lightPointColor;\n' +
    'varying vec3 v_lightAmbientColor;\n' +
    'uniform vec3 u_foggyColor;\n' +             // 雾化参数
    'uniform vec2 u_foggyDist;\n' +
    'varying float v_Dist;\n' +
    'void main() {\n' +
    '  vec4 color = texture2D(u_Sampler, v_TexCoord);\n' +
       // Calculate the color due to diffuse reflection （计算方向光）
    '  vec3 diffuse = color.rgb * nDotLightDirection;\n' +
    '  vec3 point = v_lightPointColor * color.rgb * nDotLightPoint;\n' + //计算点光源
       // Calculate the color due to ambient reflection (计算环境光)
    '  vec3 ambient = v_lightAmbientColor * color.rgb;\n' +
    '  vec4 tempColor = vec4(diffuse+point+ambient, color.a);\n' +
    '  float foggyFactor = clamp((u_foggyDist.y - v_Dist) / (u_foggyDist.y, u_foggyDist.x), 0.0, 1.0);\n' +
    '  vec3 mColor = mix(u_foggyColor, vec3(tempColor), foggyFactor);\n' +
    '  gl_FragColor =vec4(mColor, tempColor.a);\n' +
    '}\n';

// 载入model所使用的顶点着色器
let VSHADER_LIGHT2_SOURCE =
    'attribute vec4 a_Position;\n' +
    'attribute vec4 a_Color;\n' +
    'attribute vec4 a_Normal;\n' +            // Normal
    'uniform mat4 u_MvpMatrix;\n' +
    'uniform mat4 u_NormalMatrix;\n' +
    'uniform vec4 u_Eye;\n' +
    'uniform mat4 u_ModelMatrix;\n' +         // Model matrix
    'varying vec4 v_Color;\n' +
    'varying float v_Dist;\n' +
    'varying vec4 v_Position;\n' +
    'varying vec3 v_Normal;\n' +
    'void main() {\n' +
    '  gl_Position = u_MvpMatrix * a_Position;\n' +
    '  v_Normal = normalize(vec3(u_NormalMatrix * a_Normal));\n' +
    '  v_Position = u_ModelMatrix * a_Position;\n' +
    '  v_Color = a_Color;\n' +
    '  v_Dist = distance(v_Position, u_Eye);\n' +
    '}\n';

// 载入model所使用的片段着色器
let FSHADER_LIGHT2_SOURCE =
    '#ifdef GL_ES\n' +
    'precision mediump float;\n' +
    '#endif\n' +
    'uniform vec3 u_foggyColor;\n' +      //雾化颜色
    'uniform vec2 u_foggyDist;\n' +       //雾化距离
    'uniform vec3 u_LightColor;\n' +      //点光源颜色
    'uniform vec3 u_LightPosition;\n' +   // 点光源位置
    'uniform vec3 u_LightDirection;\n' +  //平行光方向
    'uniform vec3 u_AmbientLight;\n' +    // Color of an ambient light (环境光颜色)
    'varying vec4 v_Color;\n' +
    'varying vec3 v_Normal;\n' +
    'varying vec4 v_Position;\n' +
    'varying float v_Dist;\n' +
    'void main() {\n' +
    '  vec3 normal = normalize(v_Normal);\n' +
       //计算方向光
    '  float nDotLightDirection = max(dot(normal, u_LightDirection), 0.0);\n' +
    '  vec3 diffuse = v_Color.rgb * nDotLightDirection;\n' +
       //计算点光源
    '  vec3 lightDirection = normalize(u_LightPosition - vec3(v_Position));\n' +
    '  float nDotLightPoint = max(dot(lightDirection, normal), 0.0);\n' +
    '  vec3 point = u_LightColor * v_Color.rgb * nDotLightPoint;\n' +
       //计算环境光
    '  vec3 ambient = u_AmbientLight * v_Color.rgb;\n' +
    '  vec4 tempColor = vec4(ambient + diffuse + point, v_Color.a);\n' +

    '  float foggyFactor = clamp((u_foggyDist.y - v_Dist) / (u_foggyDist.y, u_foggyDist.x), 0.0, 1.0);\n' +
    '  vec3 color = mix(u_foggyColor,vec3(tempColor), foggyFactor);\n' +
    '  gl_FragColor = vec4(color, tempColor.a);\n' +
    '}\n';

let gl;
let canvas;
// 相机Eye的位置，初始为[0.0,5.0,48.0]
let cameraEye = new Vector3(CameraPara.eye);
// 相机look at的位置，初始为[0.0,5.0,43.0]
let cameraAt = new Vector3(CameraPara.at);
// 相机up方向向量，初始为[0.0,1.0,0.0]
let cameraUp = new Vector3(CameraPara.up);
// 输出的message
let pointPosition;
let light2Program;
// obj对象数组
let objectList = [];
// 存放所按下的键的数组
let keyList = [];
// 移动的距离
let distance = 0;
// 旋转的角度
let angle = 0;
// 是否打开点光源
let isPointLight = false;
// 雾化时与对象的距离
let foggyDist = new Float32Array([100, 150]);
// 雾化时与地板的距离（与地板的距离需要单独计算）
let foggyDistFloor = new Float32Array([300,300]);
// 雾化后的颜色
let foggyColor = new Float32Array([0.50, 0.50, 0.50]);

// main函数
function main() {
    // Retrieve <canvas> element
    canvas = document.getElementById('webgl');
    // Retrieve <messageBox> element
    pointPosition = document.getElementById("messageBox");
    // 视角变换矩阵
    let viewMatrix;

    // Get the rendering context for WebGL
    gl = getWebGLContext(canvas);
    if (!gl) {
        console.log('Failed to get the rendering context for WebGL');
        return;
    }

    // Initialize shaders
    light2Program = initObjectProgram(gl);
    let texProgram = initTextureProgram(gl);
    if (!light2Program || !texProgram) {
        console.log('Failed to initialize shaders.');
        return;
    }

    // 初始化obj数组，读取保存相关数据
    initObjectList();

    // 初始化地板和箱子的对象，获取相关信息
    let floorObject = initTextureVertexBuffers(gl, floorRes.vertex, floorRes.texCoord, floorRes.index, normalFloor);
    let cubeObject = initTextureVertexBuffers(gl, boxRes.vertex, boxRes.texCoord, boxRes.index, normalCube);
    if (!floorObject || !cubeObject) {
        console.log('Failed to get the object');
        return;
    }

    // 初始化地板和箱子的纹理，将其与对应图片绑定
    let textureFloor = initTextureBuffers(gl, texProgram, floorRes.texImagePath);
    let textureCube = initTextureBuffers(gl, texProgram, boxRes.texImagePath);
    if (!textureCube || !textureFloor) {
        console.log("init textureBuffer fail");
        return;
    }

    // tick函数，每隔固定时间自动重新绘制
    let tick = function () {
        // 设置为不需要点光源
        isPointLight = false;

        // 启用隐藏面消除，清除canvas
        gl.enable(gl.DEPTH_TEST);
        gl.clearColor(foggyColor[0], foggyColor[1], foggyColor[2], 1.0);
        gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

        // 更新间隔角度、位移变化
        updateAngle();

        // 根据更新的数据及键盘事件更新相机位置等信息
        updateKey();

        // 设置视角矩阵
        viewMatrix = getViewMatrix();

        // 绘制obj
        renderScene(gl, viewMatrix);

        // 绘制箱子和地板
        drawTexture(gl, texProgram, textureCube, cubeObject, viewMatrix, boxRes, false);
        drawTexture(gl, texProgram, textureFloor, floorObject, viewMatrix, floorRes, true);

        // 更新对应message
        showMassage();
        window.requestAnimationFrame(tick, canvas);
    };
    tick();

    // 设置键盘按下与松开事件
    document.onkeydown = function (ev) {
        keydown(ev);
    };

    document.onkeyup = function (ev) {
        keyup(ev);
    };
}

// 初始化model的着色器，获取相应的属性
function initObjectProgram(gl) {
    let light2Program = createProgram(gl, VSHADER_LIGHT2_SOURCE, FSHADER_LIGHT2_SOURCE);
    if (!light2Program) {
        console.log('Failed to initialize object shaders.');
        return null;
    }

    light2Program.a_Position = gl.getAttribLocation(light2Program, 'a_Position');
    light2Program.a_Color = gl.getAttribLocation(light2Program, 'a_Color');
    light2Program.u_MvpMatrix = gl.getUniformLocation(light2Program, 'u_MvpMatrix');
    light2Program.u_LightColor = gl.getUniformLocation(light2Program, 'u_LightColor');
    light2Program.u_LightPosition = gl.getUniformLocation(light2Program, 'u_LightPosition');
    light2Program.u_LightDirection = gl.getUniformLocation(light2Program, 'u_LightDirection');
    light2Program.a_Normal = gl.getAttribLocation(light2Program, 'a_Normal');
    light2Program.u_NormalMatrix = gl.getUniformLocation(light2Program, 'u_NormalMatrix');
    light2Program.u_ModelMatrix = gl.getUniformLocation(light2Program, 'u_ModelMatrix');
    light2Program.u_AmbientLight = gl.getUniformLocation(light2Program, 'u_AmbientLight');
    light2Program.u_Eye = gl.getUniformLocation(light2Program, 'u_Eye');
    light2Program.u_foggyColor = gl.getUniformLocation(light2Program, 'u_foggyColor');
    light2Program.u_foggyDist = gl.getUniformLocation(light2Program, 'u_foggyDist');

    if (light2Program.a_Position < 0 || light2Program.a_Color < 0 || light2Program.a_Normal < 0 || !light2Program.u_MvpMatrix || !light2Program.u_NormalMatrix || !light2Program.u_LightDirection) {
        console.log('Failed to get the storage location of attribute or uniform variable');
        return null;
    }
    return light2Program;
}

// 初始化纹理的shader，获取相应的属性
function initTextureProgram(gl) {
    let texProgram = createProgram(gl, TEXTURE_VSHADER_SOURCE, TEXTURE_FSHADER_SOURCE);
    if (!texProgram) {
        console.log('Failed to initialize texture shaders.');
        return null;
    }

    texProgram.a_Color = gl.getAttribLocation(texProgram, 'a_Color');
    texProgram.a_Position = gl.getAttribLocation(texProgram, 'a_Position');
    texProgram.a_TexCoord = gl.getAttribLocation(texProgram, 'a_TexCoord');
    texProgram.u_MvpMatrix = gl.getUniformLocation(texProgram, 'u_MvpMatrix');
    texProgram.u_Sampler = gl.getUniformLocation(texProgram, 'u_Sampler');
    texProgram.a_Normal = gl.getAttribLocation(texProgram, 'a_Normal');
    texProgram.u_NormalMatrix = gl.getUniformLocation(texProgram, 'u_NormalMatrix');
    texProgram.u_ModelMatrix = gl.getUniformLocation(texProgram, 'u_ModelMatrix');
    texProgram.u_LightColor = gl.getUniformLocation(texProgram, 'u_LightColor');
    texProgram.u_LightPosition = gl.getUniformLocation(texProgram, 'u_LightPosition');
    texProgram.u_LightDirection = gl.getUniformLocation(texProgram, 'u_LightDirection');
    texProgram.u_AmbientLight = gl.getUniformLocation(texProgram, 'u_AmbientLight');
    texProgram.u_Eye = gl.getUniformLocation(texProgram, 'u_Eye');
    texProgram.u_foggyColor = gl.getUniformLocation(texProgram, 'u_foggyColor');
    texProgram.u_foggyDist = gl.getUniformLocation(texProgram, 'u_foggyDist');

    return texProgram;
}

// 设置相机look at的位置和投影矩阵，获取视角变换矩阵
function getViewMatrix() {
    let viewMatrix = new Matrix4();  // 视角矩阵
    let projMatrix = new Matrix4();  // 投影矩阵
    let mvpMatrix = new Matrix4();   // 视角变换矩阵

    // 设置相机look at的位置
    viewMatrix.setLookAt(cameraEye.elements[0], cameraEye.elements[1], cameraEye.elements[2], cameraAt.elements[0], cameraAt.elements[1], cameraAt.elements[2], cameraUp.elements[0], cameraUp.elements[1], cameraUp.elements[2]);

    // 设置投影矩阵
    projMatrix.setPerspective(CameraPara.fov, canvas.width / canvas.height, CameraPara.near, CameraPara.far);
    mvpMatrix.set(projMatrix).multiply(viewMatrix);
    return mvpMatrix;
}

// 根据按下的按键执行不同的事件
function updateKey() {
    for (let i = 0; i < keyList.length; i++) {
        // 获取视角向量
        let vectorEye = VectorMinus(cameraAt, cameraEye).normalize();
        // 旋转轴
        let vectorRotateAxis;
        // 旋转向量
        let vectorRotate;
        // 移动向量

        let vectorMove;
        switch (keyList[i]) {
            case 87:  // 按下W （根据时间间隔内的速度，在视角方向加上移动距离）
                vectorMove = VectorMultNum(vectorEye, distance);
                cameraEye = VectorAdd(cameraEye, vectorMove);
                cameraAt = VectorAdd(cameraAt, vectorMove);
                break;
            case 83:  // 按下S （根据时间间隔内的速度，在视角方向加上移动距离）
                vectorMove = VectorMultNum(vectorEye, distance);
                cameraEye = VectorMinus(cameraEye, vectorMove);
                cameraAt = VectorMinus(cameraAt, vectorMove);
                break;
            case 65:  // 按下A （获取视角和up所在的法向量，并计算在其法向量上移动的距离，之后再加上移动的距离）
                vectorMove = VectorCross(vectorEye, cameraUp).normalize();
                vectorMove = VectorMultNum(vectorMove, distance);
                cameraEye = VectorMinus(cameraEye, vectorMove);
                cameraAt = VectorMinus(cameraAt, vectorMove);
                break;
            case 68:  // 按下D （获取视角和up所在的法向量，并计算在其法向量上移动的距离，之后再加上移动的距离）
                vectorMove = VectorCross(vectorEye, cameraUp).normalize();
                vectorMove = VectorMultNum(vectorMove, distance);
                cameraEye = VectorAdd(cameraEye, vectorMove);
                cameraAt = VectorAdd(cameraAt, vectorMove);
                break;
            case 73:  // 按下I （先更新up的向量，之后根据旋转的角度，计算视角移动的距离，采用向量相加更新at向量）
                vectorRotateAxis = VectorCross(vectorEye, cameraUp);
                cameraUp = VectorCross(vectorRotateAxis, vectorEye).normalize();
                vectorRotate = VectorCopy(cameraUp);
                vectorRotate = VectorAdd(vectorEye, VectorMultNum(vectorRotate, Math.sin(angle)));
                cameraAt = VectorAdd(cameraEye, vectorRotate);
                break;
            case 75:  // 按下K （先更新up的向量，之后根据旋转的角度，计算视角移动的距离，采用向量相加更新at向量）
                vectorRotateAxis = VectorCross(vectorEye, cameraUp);
                cameraUp = VectorCross(vectorRotateAxis, vectorEye).normalize();
                vectorRotate = VectorReverse(cameraUp);
                vectorRotate = VectorAdd(vectorEye, VectorMultNum(vectorRotate, Math.sin(angle)));
                cameraAt = VectorAdd(cameraEye, vectorRotate);
                break;
            case 74:  // 按下J （获取法向量（旋转方向），根据旋转角度计算旋转距离，再更新at向量）
                vectorRotate = VectorCross(vectorEye, cameraUp).normalize();
                vectorRotate = VectorReverse(vectorRotate);
                vectorRotate = VectorAdd(vectorEye, VectorMultNum(vectorRotate, Math.sin(angle)));
                cameraAt = VectorAdd(cameraEye, vectorRotate);
                break;
            case 76:  // 按下L （获取法向量（旋转方向），根据旋转角度计算旋转距离，再更新at向量）
                vectorRotate = VectorCross(vectorEye, cameraUp).normalize();
                vectorRotate = VectorAdd(vectorEye, VectorMultNum(vectorRotate, Math.sin(angle)));
                cameraAt = VectorAdd(cameraEye, vectorRotate);
                break;
            case 70:  // 按下F （打开点光源）
                isPointLight = true;
                break;
        }
    }
}

// 键盘按下事件 （每次判断keyList数组里是否有该键，若没有则添加按键）
function keydown(ev) {
    if (keyList.indexOf(ev.keyCode) === -1) {
        keyList.push(ev.keyCode);
    }
}

// 键盘抬起事件 （把对应的keyCode从keyList数组中删除）
function keyup(ev) {
    let index = keyList.indexOf(ev.keyCode);
    if (index !== -1) {
        keyList.splice(index, 1);
    }
}

// 需要读取的Object
let SceneObj = function () {
    this.model;  	 //a model contains some vertex buffer
    this.filePath;   //obj file path
    this.objDoc;
    this.drawingInfo;
    this.transform;
    this.valid = 0;
};

// 初始化objectList数组，根据scene.js获取对象，读取相关信息
function initObjectList() {
    for (let i = 0; i < ObjectList.length; i++) {
        let tempObj = new SceneObj();

        // 初始化model，获取颜色等信息
        tempObj.model = initObjectVertexBuffers(gl, light2Program);
        if (!tempObj.model) {
            console.log('Failed to set the vertex information');
            tempObj.valid = 0;
            continue;
        }

        tempObj.valid = 1;
        tempObj.kads = ObjectList[i].kads;
        tempObj.transform = ObjectList[i].transform;
        tempObj.objFilePath = ObjectList[i].objFilePath;
        tempObj.color = ObjectList[i].color;

        // 补齐alpha值
        if (tempObj.color.length === 3) {
            tempObj.color.push(1.0);
        }

        // 读取obj文件
        readOBJFile(tempObj, gl, 1.0, true);

        // 放入objectList数组中
        objectList.push(tempObj);
    }
}

// obj Model Matrix
let obj_modelMatrix = new Matrix4();
// Model view projection matrix
let obj_mvpMatrix = new Matrix4();
// obj的法向量矩阵
let obj_normalMatrix = new Matrix4();
// 小鸟旋转的角度
let birdAngle = 0;
// 小鸟每秒旋转的角度
let ANGLE_STEP = 60;

// 绘制obj
function renderScene(gl, viewMatrix) {
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
    gl.useProgram(light2Program);

    // 设置雾化的相关属性
    gl.uniform4fv(light2Program.u_Eye, new Float32Array([cameraEye.elements[0], cameraEye.elements[1], cameraEye.elements[2], 0]));
    gl.uniform3fv(light2Program.u_foggyColor, foggyColor);
    gl.uniform2fv(light2Program.u_foggyDist, foggyDist);

    // 设置方向光、环境光等光源信息
    gl.uniform3f(light2Program.u_LightDirection, sceneDirectionLight[0], sceneDirectionLight[1], sceneDirectionLight[2]);
    gl.uniform3f(light2Program.u_AmbientLight, sceneAmbientLight[0], sceneAmbientLight[1], sceneAmbientLight[2]);

    // 根据是否打开点光源设置点光源属性
    if (isPointLight) {
        gl.uniform3f(light2Program.u_LightColor, scenePointLightColor[0], scenePointLightColor[1], scenePointLightColor[2]);
        gl.uniform3f(light2Program.u_LightPosition, cameraEye.elements[0], cameraEye.elements[1], cameraEye.elements[2]);
    } else {
        gl.uniform3f(light2Program.u_LightColor, 0, 0, 0);
        gl.uniform3f(light2Program.u_LightPosition, 0, 0, 0);
    }

    // 遍历objectList数组，绘制obj
    for (let i = 0; i < objectList.length; i++) {
        let so = objectList[i];

        // 判断是否已完整读取文件，没有完成就读取文件
        if (so.objDoc != null && so.objDoc.isMTLComplete()) { // OBJ and all MTLs are available
            so.drawingInfo = onReadComplete(gl, so.model, so.objDoc);
            objectList[i].objname = so.objDoc.objects[0].name;
            so.objname = so.objDoc.objects[0].name;
            so.objDoc = null;
        }

        // 判断是否有读取信息，有读取信息则进行绘制
        if (so.drawingInfo) {
            // 设置obj的model矩阵
            obj_modelMatrix.setIdentity();
            let transform = so.transform;
            if (i !== 1) {
                // 不是小鸟，则按配置文件进行设置
                for (let j = 0; j < transform.length; j++) {
                    let content = transform[j].content;
                    switch (transform[j].type) {
                        case "translate":
                            obj_modelMatrix.translate(content[0], content[1], content[2]);
                            break;
                        case "rotate":
                            obj_modelMatrix.rotate(content[0], content[1], content[2], content[3]);
                            break;
                        case "scale":
                            obj_modelMatrix.scale(content[0], content[1], content[2]);
                            break;
                    }
                }
            } else {
                // 是小鸟，改变旋转、平移矩阵以实现动画效果
                let content = transform[1].content;
                obj_modelMatrix.scale(content[0], content[1], content[2]);
                obj_modelMatrix.rotate(birdAngle * 2, 0, 1, 0);
                obj_modelMatrix.translate(1, 0.5 * Math.sin(birdAngle * (2 * Math.PI / 360)) + 1, 1);
            }

            // 绑定model和mvp矩阵
            gl.uniformMatrix4fv(light2Program.u_ModelMatrix, false, obj_modelMatrix.elements);
            obj_mvpMatrix.set(viewMatrix).multiply(obj_modelMatrix);
            gl.uniformMatrix4fv(light2Program.u_MvpMatrix, false, obj_mvpMatrix.elements);

            // 设置法向量矩阵
            obj_normalMatrix.setInverseOf(obj_modelMatrix);
            obj_normalMatrix.transpose();
            gl.uniformMatrix4fv(light2Program.u_NormalMatrix, false, obj_normalMatrix.elements);

            // 设置position等矩阵
            initAttributeVariable(gl, light2Program.a_Position, so.model.vertexBuffer);  // Vertex coordinates
            initAttributeVariable(gl, light2Program.a_Normal, so.model.normalBuffer);    // Normal
            initAttributeVariable(gl, light2Program.a_Color, so.model.colorBuffer);// Texture coordinates
            gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, so.model.indexBuffer);

            // 进行绘制
            gl.drawElements(gl.TRIANGLES, so.drawingInfo.indices.length, gl.UNSIGNED_SHORT, 0);
        }
    }
}

let tex_mvpMatrix = new Matrix4();
let tex_modelMatrix = new Matrix4();
let tex_normalMatrix = new Matrix4();
function drawTexture(gl, texProgram, texture, object, viewMatrix, res, isFloor) {
    // 选择使用哪个program
    gl.useProgram(texProgram);

    // 绑定变量
    initAttributeVariable(gl, texProgram.a_Position, object.vertexBuffer);  // Vertex coordinates
    initAttributeVariable(gl, texProgram.a_TexCoord, object.texCoordBuffer);// Texture coordinates
    initAttributeVariable(gl, texProgram.a_Normal, object.normalBuffer);
    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, object.indexBuffer); // Bind indices
    gl.activeTexture(gl.TEXTURE0);
    gl.bindTexture(gl.TEXTURE_2D, texture);

    // 设置model矩阵
    tex_modelMatrix.setTranslate(res.translate[0], res.translate[1], res.translate[2]);
    tex_modelMatrix.scale(res.scale[0], res.scale[1], res.scale[2]);
    tex_mvpMatrix.set(viewMatrix).multiply(tex_modelMatrix);

    // 设置雾化的相关属性
    gl.uniform4fv(texProgram.u_Eye, new Float32Array([cameraEye.elements[0], cameraEye.elements[1], cameraEye.elements[2], 0]));
    gl.uniform3fv(texProgram.u_foggyColor, foggyColor);
    if (isFloor) {
        gl.uniform2fv(texProgram.u_foggyDist, foggyDistFloor);
    } else {
        gl.uniform2fv(texProgram.u_foggyDist, foggyDist);
    }

    // 设置光源的相关属性
    gl.uniform3f(texProgram.u_LightDirection, sceneDirectionLight[0], sceneDirectionLight[1], sceneDirectionLight[2]);
    gl.uniform3f(texProgram.u_AmbientLight, sceneAmbientLight[0], sceneAmbientLight[1], sceneAmbientLight[2]);
    if (isPointLight) {
        gl.uniform3f(texProgram.u_LightColor, scenePointLightColor[0], scenePointLightColor[1], scenePointLightColor[2]);
        gl.uniform3f(texProgram.u_LightPosition, cameraEye.elements[0], cameraEye.elements[1], cameraEye.elements[2]);
    } else {
        gl.uniform3f(texProgram.u_LightColor, 0, 0, 0);
        gl.uniform3f(texProgram.u_LightPosition, 0, 0, 0);
    }
    gl.uniformMatrix4fv(texProgram.u_ModelMatrix, false, tex_modelMatrix.elements);
    tex_normalMatrix.setInverseOf(tex_modelMatrix);
    tex_normalMatrix.transpose();
    gl.uniformMatrix4fv(texProgram.u_NormalMatrix, false, tex_normalMatrix.elements);
    gl.uniformMatrix4fv(texProgram.u_MvpMatrix, false, tex_mvpMatrix.elements);
    gl.drawElements(gl.TRIANGLES, object.numIndices, object.indexBuffer.type, 0);   // Draw
}

// 存储上一次执行tick()的时间
let g_last = Date.now();
function updateAngle() {
    // 计算上一次执行tick()到现在的时间间隔
    let g_now = Date.now();
    let timeDiff = g_now - g_last;

    // 根据时间间隔计算移动距离、角度，以及小鸟当前的角度
    distance = MOVE_VELOCITY * timeDiff / 1000.0;
    angle = ROT_VELOCITY * timeDiff / 1000.0 * (2 * Math.PI / 360);
    birdAngle = (birdAngle + (ANGLE_STEP * timeDiff) / 1000.0) % 360;
    g_last = g_now;
}

function showMassage() {
    pointPosition.innerHTML = "message:<br/>" +
        "position : : " + cameraEye.elements[0].toFixed(1) + ", " + cameraEye.elements[1].toFixed(1) + ", " + cameraEye.elements[2].toFixed(1) + "<br/>" +
        "look at : : " + cameraAt.elements[0].toFixed(1) + ", " + cameraAt.elements[1].toFixed(1) + ", " + cameraAt.elements[2].toFixed(1) + "<br/>";
}