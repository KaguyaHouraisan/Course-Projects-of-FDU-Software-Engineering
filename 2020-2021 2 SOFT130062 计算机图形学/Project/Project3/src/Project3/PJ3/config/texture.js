// 地板和立方体的法向量
let normalFloor = new Float32Array([0.0, 1.0, 0.0,
    0.0, 1.0, 0.0,
    0.0, 1.0, 0.0,
    0.0, 1.0, 0.0]);
let normalCube = new Float32Array([   // Normal
    0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0,         // v0-v1-v2-v3 front
    1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0,         // v0-v3-v4-v5 right
    0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0,         // v0-v5-v6-v1 up
    -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0,     // v1-v6-v7-v2 left
    0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0,     // v7-v4-v3-v2 down
    0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0, 0.0, 0.0, -1.0      // v4-v7-v6-v5 back
]);

// 初始化texture的顶点
function initTextureVertexBuffers(gl, vertice, texcoord, indices, normal) {
    // Create a cube
    //    v6----- v5
    //   /|      /|
    //  v1------v0|
    //  | |     | |
    //  | |v7---|-|v4
    //  |/      |/
    //  v2------v3

    // Utilize Object to to return multiple buffer objects together
    let o = {};

    // Write vertex information to buffer object
    o.vertexBuffer = initArrayBufferForLaterUse(gl, new Float32Array(vertice), 3, gl.FLOAT);
    if (!o.vertexBuffer) {
        console.log('Failed to verticBuffer');
        return;
    }
    o.normalBuffer = initArrayBufferForLaterUse(gl, normal, 3, gl.FLOAT);
    if (!o.normalBuffer) {
        console.log('Failed to textcoordBuffer');
        return;
    }

    // o.normalBuffer = initArrayBufferForLaterUse(gl, normals, 3, gl.FLOAT);
    o.texCoordBuffer = initArrayBufferForLaterUse(gl, new Float32Array(texcoord), 2, gl.FLOAT);
    if (!o.texCoordBuffer) {
        console.log('Failed to textcoordBuffer');
        return;
    }
    o.indexBuffer = initElementArrayBufferForLaterUse(gl, new Uint8Array(indices), gl.UNSIGNED_BYTE);
    if (!o.indexBuffer) {
        console.log('Failed to indexBuffer');
        return;
    }
    if (!o.vertexBuffer || !o.texCoordBuffer || !o.indexBuffer) return null;

    o.numIndices = indices.length;

    // Unbind the buffer object
    gl.bindBuffer(gl.ARRAY_BUFFER, null);
    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, null);
    return o;
}

function initTextureBuffers(gl, texProgram, imagePath) {
    // Create a texture object
    let texture = gl.createTexture();
    if (!texture) {
        console.log('Failed to create the texture object');
        return null;
    }

    // Create a image object
    let image = new Image();
    if (!image) {
        console.log('Failed to create the image object');
        return null;
    }

    image.src = imagePath;
    image.onload = function () {
        // Write the image data to texture object
        gl.pixelStorei(gl.UNPACK_FLIP_Y_WEBGL, 1);    // Flip the image Y coordinate
        gl.activeTexture(gl.TEXTURE0);
        gl.bindTexture(gl.TEXTURE_2D, texture);

        // gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, 1, 1, 0, gl.RGBA, gl.UNSIGNED_BYTE, new Uint8Array([0, 0, 0, 255]));
        gl.texParameteri(gl.TEXTURE_2D, gl.TEXTURE_MIN_FILTER, gl.LINEAR);
        gl.texImage2D(gl.TEXTURE_2D, 0, gl.RGBA, gl.RGBA, gl.UNSIGNED_BYTE, image);

        // Pass the texure unit 0 to u_Sampler
        gl.useProgram(texProgram);
        gl.uniform1i(texProgram.u_Sampler, 0);

        // Unbind texture
        gl.bindTexture(gl.TEXTURE_2D, null);
    };
    return texture;
}

// 设置图形每个顶点对应的下标
function initElementArrayBufferForLaterUse(gl, data, type) {
    // Create a buffer object
    let buffer = gl.createBuffer();　
    if (!buffer) {
        console.log('Failed to create the buffer object');
        return null;
    }
    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, buffer);
    gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, data, gl.STATIC_DRAW);
    buffer.type = type;
    return buffer;
}

// 将顶点数据等与buffer绑定
function initArrayBufferForLaterUse(gl, data, num, type) {
    // Create a buffer object
    let buffer = gl.createBuffer();
    if (!buffer) {
        console.log('Failed to create the buffer object');
        return null;
    }

    // Write date into the buffer object
    gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
    gl.bufferData(gl.ARRAY_BUFFER, data, gl.STATIC_DRAW);

    // Keep the information necessary to assign to the attribute variable later
    buffer.num = num;
    buffer.type = type;
    return buffer;
}

function initAttributeVariable(gl, a_attribute, buffer) {
    gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
    gl.vertexAttribPointer(a_attribute, buffer.num, buffer.type, false, 0, 0);
    gl.enableVertexAttribArray(a_attribute);
}
