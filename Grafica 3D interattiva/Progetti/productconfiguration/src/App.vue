<template>
  <div id="app" class='full-screen'>
    <ProductViewer :options="options"
                   :configuration="configuration"/>
  </div>
</template>

<script>
import ProductViewer from "@/components/ProductViewer";

export default {
  name: 'app',
  components: {
    ProductViewer
  },
  data: () =>{
    return {
      options: {
        lights: {
          intensity: 1.0,
          color: '#767676'
        },
        showObjectDimensions: false
      },
      configuration: {
        model: {
          name: 'Chair',
          filetype: 'obj',
          components: [
            {
              name: 'Back',
              material: 'wood_1',
              options: ['wood_1', 'wood_2']
            },
            {
              name: 'Legs',
              material: 'wood_1',
              options: ['wood_1', 'wood_2']
            },
            {
              name: 'Support',
              material: 'wood_1',
              options: ['wood_1', 'Metal_Rust']
            },
            {
              name: 'Foam',
              material: 'fabric',
              options: ['fabric']
            }
          ],
          materials: [
            {name: 'wood_1', filetype: 'png'},
            {name: 'wood_2', filetype: 'png'},
            {name: 'fabric', filetype: 'jpg'},
            {name: 'Metal_Rust', filetype: 'png'}
          ],
          envmap: {
            name: 'indoor',
            filetype: 'png'
          }
        }
      }
    }
  }
}
</script>

<style>
  .full-screen{
    width: 100vw;
    height: 100vh;
  }
</style>
