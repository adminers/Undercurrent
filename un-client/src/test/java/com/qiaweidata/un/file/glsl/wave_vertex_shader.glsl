attribute vec4 a_position; // 顶点位置
attribute vec2 a_texCoord0; // 纹理坐标
uniform mat4 u_projTrans; // 投影变换矩阵
varying vec2 v_texCoord; // 传递给片段着色器的纹理坐标
void main() {
    v_texCoord = a_texCoord0; // 传递纹理坐标
    gl_Position = u_projTrans * a_position; // 使用投影变换矩阵
}