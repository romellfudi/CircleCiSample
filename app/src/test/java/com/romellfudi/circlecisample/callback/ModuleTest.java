package com.romellfudi.circlecisample.callback;

import com.romellfudi.circlecisample.callback.Callback;
import com.romellfudi.circlecisample.callback.Data;
import com.romellfudi.circlecisample.callback.Module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 2/10/18
 */
@RunWith(PowerMockRunner.class)
public class ModuleTest {

    @Mock
    Callback callback;

    @Captor
    private ArgumentCaptor<Data> data;

    @InjectMocks
    Module module = new Module();


    @Test
    public void serviceModule() throws Exception {
        int v = 69;
        module.service(v,callback);
        verify(callback).returnData(data.capture());
        Data dataResponse = data.getValue();
        assertThat(dataResponse, is(notNullValue()));
        assertThat(dataResponse.getValue(), is(69));

    }


    @Test
    public void splitServiceModule() throws Exception {
        int v = 99;
        module.service(v,callback);
        verify(callback).returnData(data.capture());
        Data dataResponse = data.getValue();
        assertThat(dataResponse, is(notNullValue()));
        assertThat(dataResponse.getValue(), is(90+9));

    }

}