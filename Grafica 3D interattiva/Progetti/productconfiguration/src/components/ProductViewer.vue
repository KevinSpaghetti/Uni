<template>
    <div class='full-container'>
        <Renderer :options="options" :configuration="configuration"/>
        <div class='aside' v-if="showConfigurator">
                <el-card>
                    <div slot="header">
                        <span><b>Materials</b></span>
                    </div>
                    <div>
                        <el-tabs v-model="activeName">
                            <el-tab-pane type="card"
                                         v-for="component in configuration.model.components"
                                         :key="component.name"
                                         :label="component.name"
                                         :name="component.name">
                                Material
                                <el-select v-model="configuration.model.components[configuration.model.components.indexOf(component)].material" placeholder="Material">
                                    <el-option v-for="option in component.options"
                                               :key="option"
                                               :label="option"
                                               :value="option" />
                                </el-select>
                            </el-tab-pane>
                        </el-tabs>
                    </div>

                </el-card>
                <p></p>
                <el-card>
                    <div slot="header">
                        <span><b>Lights</b></span>
                    </div>
                    <div>
                        <span>Intensity</span>
                        <el-slider v-model="options.lights.intensity"
                                   :step="1"
                                   :min="1.0"
                                   :max="5.0"
                                   show-stops />
                    </div>
                    <div class="vertical-align">
                        <span>Color</span>
                        <el-color-picker v-model="options.lights.color"
                                         size="small"
                                         color-format="hex"/>
                    </div>

                </el-card>
            </div>
    </div>
</template>

<script>
    import Renderer from "@/components/Renderer";

    export default {
        name: "ProductViewer",
        components: {
            Renderer,
        },
        props: {
            showConfigurator: {
                type: Boolean,
                required: false,
                default: true
            },
            options: {

            },
            configuration: {

            }
        },
        data: () => {
            return {
                activeName: 'Back',
            }
        },

        methods: {}
    }
</script>

<style scoped>

    .full-container{
        width: 100%;
        height: 100%;

    }

    @media only screen and (max-width: 700px) {
        .aside {
            position: relative;
            display: block;
        }

        .full-container {
            height: 70%;
        }

    }

    @media only screen and (min-width: 700px) {
        .aside {
            position: absolute;
            right: 20px;
            top: 20px;
        }
    }

    .vertical-align {
        display: flex;
        flex-flow: row;

        justify-content: space-between;
        align-items: center;
    }

</style>