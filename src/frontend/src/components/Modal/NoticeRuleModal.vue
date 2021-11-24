<template>
  <div>

    <b-modal visible centered ref="my-modal" hide-footer scrollable title="이용약관" class="NoticeRuleModal" @hidden="hideModal">
      <div class="d-block text-center">
        <p class="my-4">1. 사석화 감지시 즉시 퇴실조치 되며 이후 도서관 이용에 제약이 있을 수 있습니다.</p>
        <p class="my-4">2. 퇴실조치 된 자리를 사용하고자 하시는 분은 해당 좌석의 유실물을 직접 유실물 보관소로 옮겨주시기 바랍니다.</p>
        <p class="my-4">3. 본 도서관에서는 위의 과정에 따른 유실물에 대한 책임을 지지 않습니다.</p>
        <p class="my-4">4. 유실물이 있다면 좌측의 짐 관리 게시판에서 확인해 주시기 바랍니다.</p>
      </div>
      <b-form-checkbox id="checkbox"
      class = "noticeCheckBox"
      v-model="status"
      name="checkbox"
      value="accepted"
      >

        <p>동의</p>

      </b-form-checkbox>
      <b-button :disabled="!status" class="mt-2" variant="outline-warning" block @click="closeWithAccept">동의하고 사용하기</b-button>
    </b-modal>
  </div>
</template>

<script>
  export default {
    props: {
      show : {
        default : false
      },
      index : Number

    },
    data(){

      return{
        status : false


      }


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
         this.$emit('closeNotice')
      }

      }
    },
    methods: {
      closeWithAccept(){

        this.$refs['my-modal'].hide()
        this.$emit('closeWithAccept',this.index)


      },
      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
        this.$emit('closeNotice')

      },
    },
    destroyed(){

      this.$emit('closeNotice')
    }
  }
</script>

<style scoped>

p{

  font-weight :bolder;

}

.noticeCheckBox{

  left : 80%;
}
</style>
