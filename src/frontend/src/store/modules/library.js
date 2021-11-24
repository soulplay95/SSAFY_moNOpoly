

export const state = {

  libararyArea : null,
  libraryName : null,
  libraryId : null,
  seatCount : null,

  libraryList : []


}

export const getters =
{

    getLibraryArea : state => state.libararyArea,
    getLibraryName : state => state.libraryName,
    getLibraryList : state=>state.libraryList,
    getLibraryId : state=>state.libraryId,
    getSeatCount : state =>state.seatCount,
    getLibraryList : state =>state.libraryList

  // getSeatData : state => state.layout,
  // getColNum : state => state.colNum,
  // getIndex : state => state.index


}



export const mutations ={
  clearAll(state)
  {

    state.libararyArea = null,
    state.libraryName = null,
    state.libraryId = null,
    state.seatCount = null,

    state.libraryList = []


  },

  clearLibraryList(state)
  {
    state.libraryList=[]
  },
  updateLibraryList(state,newList)
  {
    state.libraryList.push(newList)
  },

  updateLibraryArea(state,area)
  {
    state.libararyArea=area


  },
  updateLibraryName(state,name)
  {
    state.libraryName = name


  },
  updateLibraryId(state,id)
  {
    state.libraryId = id
  },
  updateSeatCount(state,count)
  {
    state.seatCount = count
  }

  // updateSeat(state,newLayOut)
  //   {
  //     state.layout = newLayOut;


  //   }
  //   ,
  //   updateColnum(state,newColNum)
  //   {
  //     state.colNum = newColNum;

  //   }
  //   ,
  //   updateIndex(state,newIndex)
  //   {

  //     state.index = newIndex;

  //   }



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
