package com.liumapp.blog.aop.springboot;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author liumapp
 * @file SpringBootAopTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/7/31
 */
public class SpringBootAopTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String profiles;

    @Before
    public void init() {
        this.profiles = System.getProperty("spring.profiles.active");
    }

    @After
    public void after() {
        if (this.profiles != null) {
            System.setProperty("spring.profiles.active", this.profiles);
        }
        else {
            System.clearProperty("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultSettings() throws Exception {
        SpringBootAopConsoleMain.main(new String[0]);
        String output = this.outputCapture.toString();
        assertThat(output).contains("Hello zhangsan");
    }

    @Test
    public void testCommandLineOverrides() throws Exception {
        SpringBootAopConsoleMain.main(new String[] { "--custom.name=lisi" });
        String output = this.outputCapture.toString();
        assertThat(output).contains("Hello lisi");
    }

}
