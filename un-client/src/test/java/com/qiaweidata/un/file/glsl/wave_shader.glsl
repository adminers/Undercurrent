#ifdef GL_ES
precision mediump float;
#endif

uniform vec3 iResolution; // 画布分辨率
uniform float iTime; // 时间
uniform sampler2D u_texture; // 纹理 uniform

varying vec2 v_texCoord; // 从顶点着色器传递的纹理坐标

void main() {

    vec2 fragCoord = v_texCoord * iResolution;
    vec2 uv = v_texCoord; // 使用传递过来的纹理坐标
    float pi2 = 3.1415926535 * 2.0;
    float waves = 1.0;
    float speed = 4.0;
    float smallWaves = 20.;
    float height = 30.0;
    vec4 c = vec4(0.0, 0.0, 0.0, 0.0);
    // 计算波浪效果
    //if (sin(uv.x * pi2 * waves + iTime * speed) - 0.3 * abs(sin(uv.x * pi2 * waves * 30.0 + iTime * 0.7 * speed)) > (uv.y - 0.5) * height) {
    //    c = vec3(0.7, 0.2, 0.9); // 设置颜色
    //}

    float A = sin(uv.x*waves*pi2+iTime*speed);
    float B = 0.5*abs(sin(uv.x*waves*pi2*smallWaves+iTime*speed));


    if(A-B <= (uv.y-0.5)*height){
        c = vec4(0.012, 0.988, 0.957, 0.457);
    }
    //gl_FragColor = vec4(c,1.);

    // 确保iResolution的z分量也被使用（防止被优化掉）
    float aspectRatio = iResolution.z;
    uv.y *= aspectRatio;  // 补偿宽高比
    uv.x *= iResolution.x / iResolution.y; // 补偿宽高比

    // 使用纹理颜色
    vec4 textureColor = texture2D(u_texture, uv);

    //gl_FragColor = vec4(c * textureColor); // 输出颜色
    gl_FragColor = vec4(c.rgb, textureColor.a * 0.457);
    //fragColor = vec4(c , 1.0); // 输出颜色
}