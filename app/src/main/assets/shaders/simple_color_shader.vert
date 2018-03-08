#version 300 es
precision mediump float;

layout(location = 0) in vec3 vertexPosition;

layout(location = 0) uniform vec4 color;


out vec4 vColor;

void main()
{
	gl_Position = vec4(vertexPosition, 1.0);
    vColor = color;
}