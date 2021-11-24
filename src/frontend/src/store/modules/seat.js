

export const state = {
  layout: [
    {x:16, y :0,  w :4, h :4, i  :  "출입문" ,  used :false, id: null},
    {x:0, y :0, w :2, h :2, i : 1 , used  : true,id:"ssafy1"} ,
    {x:2, y :0, w :2, h :2, i : 2 , used  : true,id:"ssafy2"},
    {x:4, y :0, w :2, h :2, i : 3 , used  : false ,id:null},
    {x:6, y :0, w :2, h :2, i : 4 , used  : true ,id:"ssafy3"},
    { x :8, y :0, w :2, h :2, i : 5 , used  : true ,id:"ssafy4"},
],
  colNum : 20,
  index: 6,

}

export const getters =
{

  getSeatData : state => state.layout,
  getColNum : state => state.colNum,
  getIndex : state => state.index


}



export const mutations ={

  updateSeat(state,newLayOut)
    {
      state.layout = newLayOut;


    }
    ,
    updateColnum(state,newColNum)
    {
      state.colNum = newColNum;

    }
    ,
    updateIndex(state,newIndex)
    {

      state.index = newIndex;

    }



}

export const actions ={



}


export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
