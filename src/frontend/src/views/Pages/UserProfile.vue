<template>
  <div>
    <!-- <base-header class="header pb-8 pt-5 pt-lg-8 d-flex align-items-center profile-header"> -->
    <base-header class="header pb-6 pb-8 pt-5 pt-md-8 d-flex align-items-center profile-header">
      <b-container fluid>
        <!-- Mask -->
        <span class="mask  opacity-8"></span>
        <!-- Header container -->
        <b-container fluid class="d-flex align-items-center">
          <b-row >
            <b-col>

            </b-col>
          </b-row>
        </b-container>
      </b-container>
    </base-header>

    <b-container fluid class="mt--6">
      <b-row>
        <b-col  class="order-xl-1">
          <edit-profile-form></edit-profile-form>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
  import EditProfileForm from './UserProfile/EditProfileForm.vue';


  export default {
    components: {
      EditProfileForm,
    }
  };
</script>
<style>
</style>
