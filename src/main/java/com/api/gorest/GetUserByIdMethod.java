package com.api.gorest;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;


@Endpoint(url = "https://gorest.co.in/public/v2/users/${id}", methodType = HttpMethodType.GET)
@SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
public class GetUserByIdMethod extends AbstractApiMethodV2 {
    public GetUserByIdMethod(String id) {
        replaceUrlPlaceholder("id", id);
    }
}
