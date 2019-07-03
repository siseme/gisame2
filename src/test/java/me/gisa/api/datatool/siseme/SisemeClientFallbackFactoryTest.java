package me.gisa.api.datatool.siseme;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SisemeClientFallbackFactoryTest {
    private SisemeClientFallbackFactory factory;

    @Before
    public void setUp() {
        factory = new SisemeClientFallbackFactory();
    }

    @Test
    public void alwaysEmptyList() {
        SisemeClient sisemeClient = factory.create(new RuntimeException(""));
        assertThat(sisemeClient.getRegionList(null)).isEmpty();
    }
}