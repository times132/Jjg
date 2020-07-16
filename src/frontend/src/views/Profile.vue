<template>
    <div>
        <div>profile</div>
        <div>
            <label>User info:</label>
            <pre>{{user}}</pre>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                user: null,
            }
        },
        created() {
            axios.get('http://localhost:9000/user/me')
                .then(({data}) => (this.user = data))
        }
    }
</script>

<style scoped>

</style>