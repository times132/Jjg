<template>
<!--    <b-form-file-->
<!--            accept="image/*"-->
<!--            browse-text="대표 사진"-->
<!--            placeholder=""-->
<!--            v-on:input="selectImage"-->
<!--    />-->
    <label for="input-img-icon">
        <img class="upload-img" src="@/asset/photo.png"/>
    </label>
    <input id="input-img-icon" class="input-image" type="file" name="uploadFile" v-on:input="selectImage"/>
<!--    <b-form-file-->
<!--            id="input-img-icon"-->
<!--            plain-->
<!--            v-on:input="selectImage"-->
<!--    />-->
</template>

<script>
    export default {
        name: "FileUpload",
        props: [],
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