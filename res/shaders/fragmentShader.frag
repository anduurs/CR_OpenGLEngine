#version 130

in vec2 texCoord;
in vec3 outColor;

uniform sampler2D sampler;

void main() 
{
	gl_FragColor = texture2D(sampler, texCoord.xy)  * 1;
	//gl_FragColor = vec4(outColor,1) * 0.7;
}