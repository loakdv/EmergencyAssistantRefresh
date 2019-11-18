/*
 *
 *  Created by Dmitry Garmyshev on 18.11.19 18:03
 *  Copyright (c) 2019 . All rights reserved.
 *  Last modified 18.11.19 18:03
 *
 */

package com.example.dmitriy.emergencyassistant.retrofit.api;

import com.example.dmitriy.emergencyassistant.model.organization.Organization;

import java.util.List;

import devs.mulham.horizontalcalendar.utils.HorizontalCalendarPredicate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrganizationApi {

    public static final String PREFIX = "/emergency/api/v1/organization";

    @GET(PREFIX+"/list")
    Call<List<Organization>> getOrganizationList();

    @GET(PREFIX)
    Call<List<Organization>> getFirstOrganizationList();

    @GET(PREFIX+"/{id}")
    Call<List<Organization>> getOrganization(@Path("id") Organization organization);

    @POST
    Call<Organization> addOrganization(@Body Organization organization);


}
