package shoppingmall.ecommerce.batch.jobconfig.product.upload;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import shoppingmall.ecommerce.batch.jobconfig.BaseBatchIntegrationTest;
import shoppingmall.ecommerce.batch.service.product.ProductService;

import javax.sql.DataSource;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = {"spring.batch.job.name=productUploadJob"})
class ProductUploadJobConfigurationTest extends BaseBatchIntegrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Value("classpath:/data/products_for_upload_csv")
    private Resource input;

    @Autowired
    ProductService productService;

    @Test
    void testJob(@Autowired Job productUploadJob) throws Exception {
        JobParameters jobParameters = jobParameters();
        jobLauncherTestUtils.setJob(productUploadJob);

        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertAll(() -> assertThat(productService.countProducts()).isEqualTo(6),

                () -> assertJobCompleted(jobExecution));
    }

    private JobParameters jobParameters() throws IOException {
        JobParameters jobParameters;
       return  jobParameters = new JobParametersBuilder()
                .addJobParameter
                        ("inputFilePath",
                                new JobParameter<>(input.getFile().getPath(), String.class,false))
                .toJobParameters();
    }
}