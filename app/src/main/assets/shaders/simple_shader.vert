#version 300 es
precision mediump float;

layout(location = 1) in vec3 vertexPosition;

void main()
{
	gl_Position = vec4(vertexPosition, 1.0);
}