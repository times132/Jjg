<template>
    <b-form-file
            accept="image/*"
            browse-text="대표 사진"
            placeholder=""
            v-on:input="selectImage"
    />
</template>

<script>
    export default {
        name: "FileUpload",
        data() {
            return {
                file: null
            }
        },
        methods: {
            selectImage(file) {
                this.$emit('event-data', file)
            }
        }
    }
</script>

<style>
    .custom-file-label{
        display: inline-block;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    .custom-file-label text{
        float: left;
    }
</style>