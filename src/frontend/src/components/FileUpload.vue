<template>
    <b-form-group label-for="file-default" label-cols-sm="2">
        <b-form-file id="file-default"></b-form-file>
    </b-form-group>
</template>

<script>
    export default {
        name: "FileUpload"
    }
</script>

<style>
    .custom-file-input:lang(en) ~ .custom-file-label::after {
        content: '업로드';
    }
</style>