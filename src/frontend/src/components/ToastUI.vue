<template>
    <Editor
            ref="tuiEditor"
            height="500px"
            initialEditType="wysiwyg"
            :initialValue="form.content"
    />
</template>

<script>
    import '../css/codemirror.css'
    import '@toast-ui/editor/dist/toastui-editor.css'

    import { Editor } from '@toast-ui/vue-editor'

    // import axios from "axios";

    export default {
        name: "ToastUI",
        props: ['initialValue', 'a'],
        data: function() {
            return {
                form: {
                    writer: '',
                    content: this.initialValue
                }
            }
        },
        components: {
            Editor
        },
        methods: {
            // submit: function (event) {
            //     event.preventDefault()
            //     this.form.content = this.$refs.tuiEditor.invoke('getHtml')
            //     this.form.writer = this.$store.state.login.userInfo.username
            //     alert(JSON.stringify(this.form))
            //     axios.defaults.headers.post['Content-Type'] = 'application/json'
            //     axios.post('http://localhost:9000/goods', JSON.stringify(this.form))
            //         .then(() => {
            //             alert("등록되었습니다.")
            //             this.$router.replace(this.$route.query.redirect || '/goods')
            //         })
            //         .catch(() => alert("실패하였습니다."))
            // }
            changeHtml: function () {
                return this.$refs.tuiEditor.invoke('getHtml')
            }
        }
    }
</script>