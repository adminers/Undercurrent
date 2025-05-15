//attribute vec4 a_position;    // 顶点位置（来自Mesh或SpriteBatch）
//attribute vec2 a_texCoord0;   // 纹理坐标（UV）
//
//uniform mat4 u_projTrans;     // 投影+视图矩阵（LibGDX默认传递的MVP矩阵）
//
//varying vec2 v_texCoord;      // 传递给片段着色器的纹理坐标
//
//void main() {
//    v_texCoord = a_texCoord0; // 直接传递UV坐标
//    gl_Position = u_projTrans * a_position; // 计算最终顶点位置
//}

attribute vec4 a_position;    // 顶点位置（直接传NDC坐标：[-1,1]）
attribute vec2 a_texCoord0;   // 纹理坐标（[0,1]）

varying vec2 v_texCoord;      // 传递给片段着色器的UV

void main() {
    v_texCoord = a_texCoord0;
    gl_Position = a_position; // 直接使用NDC坐标，无需矩阵变换
}