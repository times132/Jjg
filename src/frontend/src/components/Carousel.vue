<template>
    <div id="carousel">
        <b-carousel
                style="text-shadow: 0px 0px 2px #000"
                indicators
                controls
                img-width="1140"
                img-height="240"
        >
            <b-carousel-slide
                    caption="First slide"
                    :img-src="iu"
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
        data() {
            return {
                iu: require('@/assets/iu-1.jpg')
            }
        }
    }
</script>

<style scoped>
    @import "../css/indicator.css";
    .carousel-item{
        max-height: 240px;
    }
    .carousel-item img{
        width: 100%;
        height: 20vh;
    }
</style>