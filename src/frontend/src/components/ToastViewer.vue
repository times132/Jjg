<template>
    <div>
        <Viewer v-if="content != null" :initialValue="content"/>
    </div>
</template>

<script>
    import '@toast-ui/editor/dist/toastui-editor.css'
    import { Viewer } from '@toast-ui/vue-editor'
    import { getDetail } from '../api/index'

    export default {
        name: "ToastViewer",
        components: {
            Viewer
        },
        props: ['subUrl', 'bid'],
        data() {
            return {
                content: null
            }
        },
        created() {
            getDetail(this.subUrl, this.bid)
                .then(({data}) => this.content = data.content)
        },
    }
</script>

<style scoped>

</style>