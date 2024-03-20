package com.api.gorest;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@Endpoint(url = "https://gorest.co.in/public/v2/users/${id}", methodType = HttpMethodType.DELETE)
@SuccessfulHttpStatus(status = HttpResponseStatusType.NO_CONTENT_204)
public class DeleteUserByIdMethod extends AbstractApiMethodV2 {
    public DeleteUserByIdMethod(String id) {
        replaceUrlPlaceholder("id", id);
        setHeader("Authorization", "Bearer c7686d8a55cc0397f7b53d2358555b40fce0dbcabd8eec3edc79604bd5a35655");
    }
}
