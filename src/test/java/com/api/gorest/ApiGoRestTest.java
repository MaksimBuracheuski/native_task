package com.api.gorest;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiGoRestTest implements IAbstractTest {

    @Test
    public void getAllUsersTest() {
        GetUsersMethod api = new GetUsersMethod();
        api.callAPI();
        api.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }

    @Test
    public void getUsersByIdTest() {
        GetUsersMethod api = new GetUsersMethod();
        DocumentContext context = JsonPath.parse(api.callAPI().asString());
        List<String> ids = context.read("$..id");
        GetUserByIdMethod apiById = new GetUserByIdMethod(ids.stream().findAny().toString().replaceAll("\\D", ""));
        apiById.callAPI();
        apiById.validateResponseAgainstSchema("api/users/_get_by_id/rs.schema");
    }

    @Test
    public void deleteUserByIdTest() {
        GetUsersMethod api = new GetUsersMethod();
        DocumentContext context = JsonPath.parse(api.callAPI().asString());
        List<String> ids = context.read("$..id");
        String id = ids.stream().findAny().toString().replaceAll("\\D", "");
        DeleteUserByIdMethod apiDeleteById = new DeleteUserByIdMethod(id);
        apiDeleteById.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        apiDeleteById.callAPI();
        context = JsonPath.parse(api.callAPI().asString());
        List<Integer> idds = context.read("$..id");
        Assert.assertFalse(idds.stream().anyMatch(a -> a.equals(Integer.parseInt(id))));
    }

    @Test
    public void getAllPostsTest() {
        GetPostsMethod api = new GetPostsMethod();
        api.callAPI();
        api.validateResponseAgainstSchema("api/posts/_get/rs.schema");
    }

    @Test
    public void getAllCommentsTest() {
        GetCommentsMethod api = new GetCommentsMethod();
        api.callAPI();
        api.validateResponseAgainstSchema("api/comments/_get/rs.schema");
    }
}
