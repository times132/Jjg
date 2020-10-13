<template>
    <div id="carousel">
        <b-carousel
                style="text-shadow: 0px 0px 2px #000"
                indicators
                controls
        >
            <b-carousel-slide
                    caption="First slide"
                    img-src="https://picsum.photos/1024/480/?image=10"
            ></b-carousel-slide>
            <b-carousel-slide
                    caption="Second Slide"
                    img-src="https://picsum.photos/1024/480/?image=12"
            ></b-carousel-slide>
            <b-carousel-slide
                    caption="Third Slide"
                    img-src="https://picsum.photos/1024/480/?image=22"
            ></b-carousel-slide>
        </b-carousel>
    </div>
</template>

<script>
    export default {
        name: "Carousel",
        components: {

        }
    }
</script>

<style scoped>
    @import "../css/indicator.css";
    .carousel-item{
        max-height: 240px;
    }
    .carousel-item img{
        height: 20vh;
    }
</style>