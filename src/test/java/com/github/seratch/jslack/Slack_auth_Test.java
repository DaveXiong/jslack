package com.github.seratch.jslack;

import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.auth.AuthRevokeRequest;
import com.github.seratch.jslack.api.methods.request.auth.AuthTestRequest;
import com.github.seratch.jslack.api.methods.response.auth.AuthRevokeResponse;
import com.github.seratch.jslack.api.methods.response.auth.AuthTestResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Slf4j
public class Slack_auth_Test {

    Slack slack = Slack.getInstance();

    @Test
    public void authRevoke() throws IOException, SlackApiException {
        AuthRevokeResponse response = slack.methods().authRevoke(AuthRevokeRequest.builder().token("dummy").test("1").build());
        assertThat(response.isOk(), is(false));
        assertThat(response.getError(), is("invalid_auth"));
        assertThat(response.isRevoked(), is(false));
    }

    @Test
    public void authTest() throws IOException, SlackApiException {
        String token = System.getenv("SLACK_BOT_TEST_API_TOKEN");
        AuthTestResponse response = slack.methods().authTest(AuthTestRequest.builder().token(token).build());
        assertThat(response.isOk(), is(true));
        assertThat(response.getUrl(), is(notNullValue()));
    }

}