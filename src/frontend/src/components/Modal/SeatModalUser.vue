<template>
  <div>

    <b-modal visible centered ref="my-modal" hide-footer v-bind:title="'좌석번호 '+seatData.i" class="seatDataModal" @hidden="hideModal">
      <div class="d-block text-center">
        <p  >좌석 번호 : {{seatData.i}}</p>
        <p  v-if="seatData.used">좌석 상태 : 사용중</p>
        <p  v-if="!seatData.used">좌석 상태 : 사용가능</p>
      </div>
      <b-button v-if="!seatData.used" class="mt-3" variant="outline-danger" block @click="reserve">사용하기</b-button>
      <b-button v-if="seatData.id == this.$store.getters['user/getUserId']" class="mt-3" variant="outline-danger" block @click="endUse">사용종료하기</b-button>
      <b-button class="mt-2" variant="outline-warning" block @click="hideModal">닫기</b-button>
    </b-modal>
  </div>
</template>

<script>
  export default {
    props: {
      seatData : Object,
      show : {
        default : false
      },
      index : Number

    },
    watch : {

      show(newval) {

      if(newVal)
      {
          this.$refs['my-modal'].show()
      }
      else
      {
         this.$refs['my-modal'].hide()
         this.$emit('close')
      }

      }
    },
    methods: {
      endUse(){


          this.$emit('endUse',this.index)
          this.$emit('close')


      },
      reserve(){
        this.$refs['my-modal'].hide()

        if(this.$store.getters['user/getUserId']==null)
        {

          alert("로그인 후 이용가능합니다.")
        }
        else
        {

          this.$emit('closeReserve')

        }





      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
        this.$emit('close')

      },
    },
    destroyed(){

      this.$emit('close')
    }
  }
</script>

<style scoped>

p{

  font-weight :bolder;

}
</style>
