<template>
    <div class="footer">
        <b-container>
            <b-row class="text-center">
                <b-col cols="2">사진</b-col>
                <b-col>
                    <b-row class="justify-content-md-center">
                        <ul class="a">
                            <li>상호 : {{ company }} | </li>
                            <li>대표 : {{ ceo }} | </li>
                            <li>대표 번호 : {{ phoneNum }} | </li>
                            <li>주소 : {{ address1 }}</li><br>
                            <li>사업자등록번호 : {{ license }} | </li>
                            <li>통신판매신고번호 : {{ mailOrder }} | </li>
                            <li>개인정보책임자 : {{ personalDataManager }}</li><br>
                            <li>Copyright ⓒ 착한 중고. All Rights Reserved | Design times132</li>
                        </ul>
                    </b-row>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: "Footer",
        data() {
            return {
                company: '착한 중고',
                ceo: '박정현',
                personalDataManager: '박병진',
                license: '111-22-33333',
                mailOrder: '1111-제주-2222',
                phoneNum: '064-711-4694',
                address1: '제주 제주시 연북로 733 착한중고',
                address2: '제주 제주시 가령로 75 중고쇼핑몰',
                email: 'times132@naver.com'
            }
        }
    }
</script>

<style scoped>
    .footer {
        position: absolute;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: lightgray;
    }
    .a li{
        display: inline-block;
        font-size: 12px;
        padding: 0 2px 0 2px;
    }
</style>