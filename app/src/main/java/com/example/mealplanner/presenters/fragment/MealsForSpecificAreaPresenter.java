package com.example.mealplanner.presenters.fragment;

import com.example.mealplanner.data.repositories.MealsForAreaRepository;
import com.example.mealplanner.model.Meal;
import com.example.mealplanner.network.NetworkCallBack;
import com.example.mealplanner.presenters.contract.MealsForSpecificAreaContract;

import java.util.List;

public class MealsForSpecificAreaPresenter implements MealsForSpecificAreaContract , NetworkCallBack {

    private MealsForSpecificAreaContract mealsForSpecificAreaContract;
    private MealsForAreaRepository mealsForAreaRepository;
    private String country;

    public MealsForSpecificAreaPresenter(final MealsForSpecificAreaContract mealsForSpecificAreaContract, final MealsForAreaRepository mealsForAreaRepository, final String country) {
        this.mealsForSpecificAreaContract = mealsForSpecificAreaContract;
        this.mealsForAreaRepository = mealsForAreaRepository;
        this.country = country;
    }

    public MealsForSpecificAreaPresenter(final MealsForSpecificAreaContract mealsForSpecificAreaContract) {
        this.mealsForSpecificAreaContract = mealsForSpecificAreaContract;
    }

    @Override
    public void getAllMeal(String country) {
        mealsForAreaRepository = new MealsForAreaRepository(mealsForSpecificAreaContract);
        mealsForAreaRepository.getAllMeals(country);
    }

    @Override
    public void addToFavorite(Meal meals) {

    }

    @Override
    public void onSuccessResult(List<Meal> meals) {

    }

    @Override
    public void onFailureResult(String errorMsg) {


    }


//    private final String TAG = "MealsList";
//    private MealsForSpecificAreaContract.View mealsView;
//    private MealsForSpecificAreaContract.Model mealsModel;
//
//    public MealsForSpecificAreaPresenter(final MealsForSpecificAreaContract.View mealsView) {
//        this.mealsView = mealsView;
//        this.mealsModel = mealsModel;
//    }
//
//    @Override
//    public void getAllMealsForArea(OnFinishedListener onFinishedListener, String areaSelected) {
//        ApiService apiService = RetrofitManager.getInstance().create(ApiService.class);
//        Call<AreaListResponse> call = apiService.getMealsOfSelectedArea(areaSelected);
//        call.enqueue(new Callback<AreaListResponse>() {
//            @Override
//            public void onResponse(Call<AreaListResponse> call, Response<AreaListResponse> response) {
//                List<MealsForArea> meals = response.body().getMeal();
//                Log.i(TAG, "onResponse: "+meals.size());
//                onFinishedListener.onFinished(meals);
//            }
//
//            @Override
//            public void onFailure(Call<AreaListResponse> call, Throwable t) {
//                Log.i(TAG, "onFailure: "+t.toString());
//                onFinishedListener.onFailure(t);
//            }
//        });
//    }
//
//    @Override
//    public void showProgress() {
//
//    }
//
//    @Override
//    public void hideProgress() {
//
//    }
//
//    @Override
//    public void setDataToRecycleView(List<MealsForArea> mealsList) {
//
//    }
//
//    @Override
//    public void onResponseFailure(Throwable t) {
//
//    }
//
//    @Override
//    public void onDestroy() {
//        this.mealsView=null;
//
//    }
//
//    @Override
//    public void getMoreData(String area) {
//        if(mealsView != null)
//        {
//            mealsView.showProgress();
//        }
//         mealsModel.getAllMealsForArea(this,area);
//    }
//
//    @Override
//    public void requestDataFromServer() {
//        if(mealsView != null)
//        {
//            mealsView.showProgress();
//        }
//        mealsModel.getAllMealsForArea(this,"Egyption");
//
//    }
//
//    @Override
//    public void onFinished(List<MealsForArea> meals) {
//        mealsView.setDataToRecycleView(meals);
//        if(mealsView != null)
//        {
//            mealsView.hideProgress();
//        }
//    }
//
//    @Override
//    public void onFailure(Throwable throwable) {
//        mealsView.onResponseFailure(throwable);
//        if(mealsView != null){
//            mealsView.hideProgress();
//        }
//
//    }

    /*private List<Meal> meals = new ArrayList<>();
    private MealsForAreaRepository areaRepository;
    private MealsForSpecificAreaFragment mealsForSpecificAreaFragment;
    private MealsForSpecificAreaContract mealsForSpecificAreaContract;

    public MealsForSpecificAreaPresenter(final MealsForSpecificAreaFragment mealsForSpecificAreaFragment) {
        this.mealsForSpecificAreaFragment = mealsForSpecificAreaFragment;
    }

    @Override
    public void getAllMealsForArea(String strArea) {
        areaRepository = new MealsForAreaRepository(mealsForSpecificAreaContract);
        areaRepository.getAllMeals(strArea);
    }*/



}
