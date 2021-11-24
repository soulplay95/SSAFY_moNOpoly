<template>
  <div>

    <b-modal visible centered ref="my-modal" hide-footer scrollable title="예약" class="NoticeRuleModal" @hidden="hideModal">
      <div class="d-block text-center">
        <p>예약현황</p>
         <b-table striped hover :items="timeTable"></b-table>
        <p>시작시간</p>
        <VueTimepicker hide-disabled-items hide-clear-button close-on-complete :hour-range="startHourRange" :minute-interval="30" @change="setStartTime" class="ml-2 mt-2 mb-5 timeTable" ></VueTimepicker>
        <p>종료시간</p>
        <VueTimepicker hide-disabled-items close-on-complete hide-clear-button :hour-range="endHourRange" :minute-interval="30" @change="setEndTime" class="ml-2 mt-2 mb-5 timeTable" ></VueTimepicker>

      </div>

      <b-button  class="mt-2" variant="outline-warning" block @click="closeWithReserve">예약</b-button>
      <b-button  class="mt-2" variant="outline-warning" block @click="hideModal">확인</b-button>
    </b-modal>
  </div>
</template>

<script>
  import VueTimepicker from 'vue2-timepicker/src/vue-timepicker.vue'
  export default {
    props: {
      show : {
        default : false
      }

    },
    components :{

      VueTimepicker

    },
    data(){

      return{
        timeTable : [

          {시간 : "12:30" , 예약여부 : "예약됨"},
          {시간 : "13:00" , 예약여부 : "예약됨"},
          {시간 : "13:30" , 예약여부 : "예약됨"},
          {시간 : "14:00" , 예약여부 : "예약됨"},
          {시간 : "14:30" , 예약여부 : "예약됨"},
          {시간 : "15:00" , 예약여부 : "예약가능"},
          {시간 : "15:30" , 예약여부 : "예약됨"},
          {시간 : "16:00" , 예약여부 : "예약됨"},
          {시간 : "16:30" , 예약여부 : "예약됨"},
          {시간 : "17:00" , 예약여부 : "예약됨"},



        ],
        startHourTime : 0,
        startMinuteTime :0,
        initStartHourTime : 0,
        initStartMinuteTime :0,
        startHourRange : [],
        endHourRange : [],
        endHourTime : 0,
        endMinuteTime:9999,



      }


    },
    mounted(){

      var datetime = new Date();
      this.initStartHourTime = Number(datetime.getHours())
      this.initStartMinuteTime = Number(datetime.getMinutes())
      if(this.initStartMinuteTime < 30)
      {
        this.initStartMinuteTime = 0
      }
      else
      {

        this.initStartMinuteTime = 30
      }

      this.startHourRange = [[this.initStartHourTime,20]]



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
         this.$emit('closeReserve')
      }

      }
    },
    methods: {
      closeWithReserve()
      {

        //통신코드추가
        this.$emit('closeReserve')

      },

      setStartTime(date)
      {
        this.startHourTime = Number(date.data.HH)
        this.startMinuteTime = Number(date.data.mm)

        this.endHourRange=[[this.startHourTime,21]]

        var day = new Date()
        console.log(day.toISOString())
        console.log(date)


      },

      setEndTime(date)
      {

        this.endHourTime = Number(date.data.HH)
        this.endMinuteTime = Number(date.data.mm)

        console.log(date)


      },

      showModal() {
        this.$refs['my-modal'].show()
      },
      hideModal() {
        this.$refs['my-modal'].hide()
        this.$emit('closeReserve')

      },
    },
    destroyed(){

      this.$emit('closeReserve')
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
