<template>
    <div class="container">
        <Viewer v-if="content != null" :initialValue="content"/>
    </div>
</template>

<script>
    import '../../css/codemirror.css'
    import '@toast-ui/editor/dist/toastui-editor.css'
    import { Viewer } from '@toast-ui/vue-editor'
    import axios from 'axios'

    export default {
        components: {
            Viewer
        },
        name: "Detail",
        data() {
            return {
                content: null,
                bid: this.$route.params.bid
            }
        },
        mounted() {
            axios.get(`http://localhost:9000/goods/detail/${this.bid}`)
                .then(({data}) => {
                    console.log(data)
                    this.content = data.content
                })
        }
    }
</script>

<style scoped>

</style>