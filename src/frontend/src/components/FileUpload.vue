<template>
    <div class="file-form">
        <label class="upload-div mb-0" for="input-img-icon">
            <img class="upload-img" :src="photo"/>
        </label>

        <div class="file-body">
            <span style="color: #938e8e">{{ fileName }}</span>
        </div>
        <input id="input-img-icon" class="input-image" type="file" name="uploadFile" v-on:input="selectImage"/>
    </div>
</template>

<script>
    export default {
        name: "FileUpload",
        props: ['initialFile'],
        data() {
            return {
                fileName: '대표 사진(10MB 이하)',
                photo: require('@/assets/photo.jpg')
            }
        },
        created() {
            if (this.initialFile != null) this.fileName = this.initialFile.fileName
        },
        methods: {
            selectImage(event) {
                this.fileName = event.target.files[0].name
                this.$emit('event-data', event.target.files[0])
            }
        }
    }
</script>

<style>
    .file-form{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        padding: .25rem;
    }
    .upload-div{
        float: left;
    }
    .upload-img{
        margin-right: 0.25rem;
        width: 1.6rem;
        height: 1.6rem;
        cursor: pointer;
    }
    .input-image{
        visibility: hidden;
        height: 0;
        float: left;
    }
</style>