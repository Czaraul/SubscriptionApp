package org.czaraul.subscriptionapp.controller.mocked;

import org.springframework.stereotype.Service;

@Service
public class MockedAWSSecretManager implements MockedSDK {
    @Override
    public String getSecretValue() {
        return "";
    }
}
