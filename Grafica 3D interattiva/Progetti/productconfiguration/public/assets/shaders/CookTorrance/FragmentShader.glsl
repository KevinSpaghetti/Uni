#extension GL_OES_standard_derivatives : enable

varying vec3 vPosition;
varying vec3 vNormal;
varying vec2 uVv;
varying vec3 wPosition;

struct PointLight {
    vec3 position;
    vec3 color;
    float intensity;
};

//Threejs lights
uniform PointLight pointLights[NUM_POINT_LIGHTS];

uniform vec3 ambientLightColor;

//Using textures
//1. Diffuse
//2. Specular
//3. Roughness
//4. Normal
uniform sampler2D diffuse;
const   vec3      specular = vec3(0.1);
uniform sampler2D roughness;
uniform sampler2D normalmap;
uniform samplerCube envmap;


//Values of textures at point
vec3 pointDiffuse;
vec3 pointSpecular;
float pointRoughness;
vec3 pointNormal;

const float PI = 3.14159;
const vec2 normalScale = vec2(1);

vec3 FSchlick(vec3 l, vec3 h){
    float lDoth = max(dot(l,h), 0.000001);
    return (pointSpecular + (vec3(1.0) - pointSpecular) * pow(1.0 - lDoth, 5.0));
}
float DGGX(vec3 n, vec3 h, float alpha){
    float nDoth = max(dot(n,h), 0.000001);
    float alpha2 = alpha * alpha;
    float d = nDoth * nDoth * (alpha2 - 1.0)+1.0;
    return (alpha2 / (PI*d*d));
}
float G1(vec3 a, vec3 b, float k){
    float aDotb = max(dot(a,b), 0.000001);
    return (aDotb / (aDotb * (1.0 - k) + k) );
}
float GSmith(vec3 n, vec3 v, vec3 l){
    float k = pointRoughness * pointRoughness;
    return G1(n, l, k) * G1(n, v, k);
}

vec3 perturbNormal2Arb( vec3 eye_pos, vec3 surf_norm ) {
    vec3 q0 = dFdx( eye_pos.xyz );
    vec3 q1 = dFdy( eye_pos.xyz );
    vec2 st0 = dFdx( uVv.st );
    vec2 st1 = dFdy( uVv.st );
    vec3 S = normalize(  q0 * st1.t - q1 * st0.t );
    vec3 T = normalize( -q0 * st1.s + q1 * st0.s );
    vec3 N =  surf_norm ;
    vec3 mapN = normalize(texture2D( normalmap, uVv ).xyz * 2.0 - 1.0);
    mapN.xy = normalScale * mapN.xy;
    mat3 tsn = mat3( S, T, N );
    return normalize( tsn * mapN );
}

vec3 inverseTransformDirection( in vec3 dir, in mat4 matrix ) {
    return normalize( ( vec4( dir, 0.0 ) * matrix ).xyz );
}

void main(){

    vec3 outRadiance = vec3(0.0);

    vec3 n = perturbNormal2Arb(vPosition, normalize( vNormal ));  // interpolation destroys normalization, so we have to normalize
    vec3 v = normalize( -vPosition);
    vec3 worldN = inverseTransformDirection( n, viewMatrix );
    vec3 worldV = cameraPosition - wPosition ;
    vec3 r = normalize( reflect(-worldV,worldN));
    float nDotv = max(dot( n, v ),0.000001);

    pointDiffuse = texture2D(diffuse, uVv).rgb;
    pointDiffuse = pow(pointDiffuse, vec3(2.2));

    pointSpecular = specular;

    pointRoughness = texture2D(roughness, uVv).r;

    /*
    vec3 envLight = textureCube( envmap, vec3(-r.x, r.yz)).rgb;
    envLight = pow( envLight, vec3(2.2));
    */

    vec4 totalLight = vec4(vec3(0.0), 1.0);

    for( int li = 0; li < NUM_POINT_LIGHTS; li++){
        vec3 lightPosition = pointLights[li].position;
        vec3 lightIntensity = pointLights[li].color;
        float lightDistance = distance(lightPosition, vPosition);

        vec4 lPosition = vec4( lightPosition, 1.0 );
        vec3 l = normalize(lPosition.xyz - vPosition.xyz);
        vec3 h = normalize( v + l);
        // small quantity to prevent divisions by 0
        float nDotl = max(dot( n, l ),0.000001);
        float lDoth = max(dot( l, h ),0.000001);
        float nDoth = max(dot( n, h ),0.000001);
        float vDoth = max(dot( v, h ),0.000001);

        vec3 fresnel = FSchlick(l, h);
        float geometryFactor = GSmith(n, v, l);
        float normalDistribution = DGGX(n, h, pointRoughness*pointRoughness);

        vec3 diffuse = ambientLightColor/vec3(NUM_POINT_LIGHTS) + pointDiffuse/PI;
        vec3 specularBRDF = (fresnel * normalDistribution * geometryFactor) / (4.0 * nDotl * nDotv);

        outRadiance += PI * lightIntensity * nDotl * (vec3(1.0) - fresnel)*diffuse + fresnel*specularBRDF;
    }

    vec4 color = vec4(pow(vec3(outRadiance), vec3(1.0/2.2)),1.0);
    gl_FragColor = color;
}
