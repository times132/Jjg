<template>
    <div class="footer">
        <b-container>
            <b-row>
                <b-col cols="2">사진</b-col>
                <b-col>
                    <span>상호 : {{ company }} | 대표 : {{ ceo }} | 대표 번호 : {{ phoneNum }}</span>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "footer",
        data() {
            return {
                company: '착한 중고',
                ceo: '박정현',
                personalDataManager: '박병진',
                license: '111-22-33333',
                mailOrder: '1111-제주-2222',
                phoneNum: '064-711-4694',
                address1: '제주 제주시 가령로 75 중고쇼핑몰',
                address2: '제주 제주시 연북로 733 착한중고',
                email: 'times132@naver.com'
            }
        }
    }
</script>

<style>
    .footer {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: lightgray;
    }
</style>