<template>
  <div>

    <b-modal visible centered ref="my-modal" hide-footer v-bind:title="'좌석번호 '+seatData.i" class="seatDataModal"  @hidden="hideModal">
      <div class="d-block text-center">
        <p  >좌석 번호 : {{seatData.i}}</p>
        <p  v-if="seatData.frontState !=0">좌석 상태 : 사용중</p>
        <p  v-if="seatData.frontState != 0">사용자 : {{userName}}</p>
        <p  v-if="seatData.frontState ==0">좌석 상태 : 사용가능</p>
      </div>
      <b-button v-if="seatData.used" class="mt-4" variant="outline-danger" block @click="updateOccupy">사용취소</b-button>
      <!-- <b-button  class="mt-3" variant="outline-danger" block @click="hideModal">예약하기</b-button> -->
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
    data(){

      return {

        userName : null

      }


    },
    mounted(){


      if(this.seatData.id != null)
      {
        this.$axios.get(this.$baseUrl+'user/'+this.seatData.id)
      .then(res =>{

        this.userName = res.data.nickname


      })


      }




    },
    methods: {
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
        this.$emit('close')

      },
      updateOccupy(){

        this.$refs['my-modal'].hide()
        this.$emit('close')
        this.$emit('updateOccupy',this.index)

      }
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
