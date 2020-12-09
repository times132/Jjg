<template>
    <b-container class="mt-3">

        <b-row class="mb-2">
            <b-col md="4">
                <b-form-select v-model="board.categoryItem" :options="options" required></b-form-select>
            </b-col>
            <b-col md="4">
                <Upload @event-data="fileInfo" :initial-file="board.image"/>
            </b-col>
            <b-col md="4" class="ml-auto">
                <b-form-input v-model="board.price" placeholder="가격" required></b-form-input>
            </b-col>

        </b-row>

        <b-row class="mb-2">
            <b-col>
                <b-form-input v-model="board.title" placeholder="제목" required></b-form-input>
            </b-col>
        </b-row>

        <b-row class="mb-2">
            <b-col>
                <ToastUI :initialValue="board.content" ref='tuiWrite'/>
            </b-col>
        </b-row>

        <b-button v-on:click="submit" squared>수정</b-button>

    </b-container>
</template>

<script>
    import ToastUI from '../../components/ToastUI'
    import Upload from '../../components/FileUpload'
    import { uploadBoardImage, updateBoard } from "../../api";

    export default {
        name: "Modify",
        components: {
            ToastUI,
            Upload
        },
        data() {
            return {
                file: null,
                board: this.$route.params.board,
                options: [
                    { value: null, text: '------------'},
                    { value: {id: 1, itemNum: '011', name: '스텐드'}, text: '스텐드'},
                    { value: {id: 2, itemNum: '012', name: '중대형'}, text: '중대형'},
                    { value: {id: 3, itemNum: '013', name: '벽걸이'}, text: '벽걸이'},
                    { value: {id: 4, itemNum: '021', name: '냉장고'}, text: '냉장고'},
                    { value: {id: 5, itemNum: '022', name: '세탁기'}, text: '세탁기'},
                    { value: {id: 6, itemNum: '023', name: 'TV'}, text: 'TV'},
                    { value: {id: 7, itemNum: '031', name: '선반'}, text: '선반'},
                    { value: {id: 8, itemNum: '032', name: '냉장/냉동고'}, text: '냉장/냉동고'},
                    { value: {id: 9, itemNum: '033', name: '화구'}, text: '화구'},
                    { value: {id: 10, itemNum: '041', name: '책상'}, text: '책상'},
                    { value: {id: 11, itemNum: '042', name: '사무2'}, text: '사무2'},
                    { value: {id: 12, itemNum: '043', name: '사무3'}, text: '사무3'}
                ]
            }
        },
        methods: {
            async submit(event) {
                event.preventDefault()
                this.board.content = this.$refs.tuiWrite.getHtml()

                const uploadData = new FormData()
                uploadData.append('file', this.file)
                uploadData.append('categoryNum', this.board.categoryItem.itemNum)

                let uploadResponse = null

                if (this.file === -1){
                    this.board.image = null
                }
                else if (this.file !== null) {
                    uploadResponse = await uploadBoardImage(uploadData)

                    this.board.image = {
                        fid: this.board.image == null ? 0 : this.board.image.fid,
                        uuid: uploadResponse.data.uuid,
                        path: uploadResponse.data.path,
                        fileName: uploadResponse.data.fileName
                    }
                }


                updateBoard(JSON.stringify(this.board), this.board.bid)
                    .then(() => {
                        alert("수정되었습니다.")
                        this.$router.go(-1)
                    })
                    .catch(() => alert("실패하였습니다."))
            },
            fileInfo(file) {
                this.file = file
            }
        }
    }
</script>

<style scoped>

</style>