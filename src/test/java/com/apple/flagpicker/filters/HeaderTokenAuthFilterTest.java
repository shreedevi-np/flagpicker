/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.filters;

import com.apple.flagpicker.FlagpickerApplicationTest;
import com.apple.flagpicker.config.FlagPickerProperties;
import com.apple.flagpicker.providers.CustomAuthenticationProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 *
 * @author MReddy
 */
public class HeaderTokenAuthFilterTest extends FlagpickerApplicationTest {

    @Autowired
    private FlagPickerProperties properties;

    @Test
    public void testAuthTokenFilter401() throws ServletException, IOException {
        HeaderTokenAuthFilter filterUnderTest = new HeaderTokenAuthFilter(new CustomAuthenticationProvider(), properties);
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        filterUnderTest.doFilterInternal(request, response, filterChain);

        Assert.assertEquals(response.getStatus(), HttpStatus.UNAUTHORIZED.value());
    }
    
    @Test
    public void testAuthTokenFilter200() throws ServletException, IOException {
        HeaderTokenAuthFilter filterUnderTest = new HeaderTokenAuthFilter(new CustomAuthenticationProvider(), properties);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("auth-token", properties.getValue("fp.security.token", ""));
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockFilterChain filterChain = new MockFilterChain();

        filterUnderTest.doFilterInternal(request, response, filterChain);

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
