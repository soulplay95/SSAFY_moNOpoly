<template>
  <div class ="main">

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 ">
    </base-header>

    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row class="justify-content-center ">
        <b-col lg="12">
          <card header-classes="bg-transparent " class="backgroundCard">
            <h3 slot="header" class="mb-0 font15">좌석 현황</h3>
              <div class= "board mt--5">
                <moving/>
              </div>
          </card>
        </b-col>
      </b-row>
    </b-container>
  </div>


</template>
<script>
  // Charts
  import LineChart from '@/components/Charts/LineChart';
  import BarChart from '@/components/Charts/BarChart';

  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';
  import moving from '@/components/SeatDragAndDrop.vue'

  // Tables
  import SocialTrafficTable from './Dashboard/SocialTrafficTable';
  import PageVisitsTable from './Dashboard/PageVisitsTable';

  export default {
    components: {
      LineChart,
      BarChart,
      BaseProgress,
      StatsCard,
      PageVisitsTable,
      SocialTrafficTable,
      moving
    },
    updated(){


    },
    data() {
      return {
      }

    },
    methods: {

    },
    mounted() {

    }
  };
</script>
<style>
.backgroundCard {
  height: 75vh;
}

.font15 {
  font-size: 1.5em;
}

.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}

.board{
  background-color :white;
  width : 100%;



}
.main{

  height : 100%;
  width : 100%;

}
</style>
