package com.example.easyFashions.controller;
import com.easy.EasyFashionsApplication;
import com.easy.model.Product;
import com.easy.model.ProductBrand;
import com.easy.model.ProductCategory;
import com.easy.service.EasyProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.apache.commons.io.IOUtils;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(EasyProductRest.class)
@SpringBootTest(classes = EasyFashionsApplication.class)
@AutoConfigureMockMvc
public class EasyProductRestTest {


    @MockBean
    EasyProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoadAllProducts() throws Exception {

        EasyProductService productService1 = mock(EasyProductService.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(productService1).loadAllProducts();

        mockMvc.perform(post("/easy/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8")).andExpect(
                content().string(equalTo("Data Loaded")));

    }


    @Test
    public void getAllProducts() throws Exception {

        when(productService.getAllProducts(any(Pageable.class))).thenReturn((Page<Product>) getAllProductsList());

        mockMvc.perform(get("/easy/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("getallproduct/getAllProduct.json")));

    }


    @Test
    public void getAllProducts_404() throws Exception {

        mockMvc.perform(get("/easy/product").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void getAllBrands() throws Exception {

        when(productService.getAllBrands(any(Pageable.class))).thenReturn((Page<ProductBrand>) getAllBrandList());
        mockMvc.perform(get("/easy/product/brand").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("getallbrand/getAllBrands.json")));

    }

    @Test
    public void getAllBrands_404() throws Exception {

        mockMvc.perform(get("/easy/product/brand").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllCategories() throws Exception {

        when(productService.getAllCategories(any(Pageable.class))).thenReturn((Page<ProductCategory>) getAllProductCategoryList());
        mockMvc.perform(get("/easy/product/category").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("getallcategory/getAllProductCategory.json")));
    }


    @Test
    public void getAllCategories_404() throws Exception {

        mockMvc.perform(get("/easy/product/category").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getProductById() throws Exception {

        when(productService.getProductById(1)).thenReturn(getProduct_1());

        mockMvc.perform(get("/easy/product/{prdid}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("productbyid/getproduct_1.json")));
    }

    @Test
    public void getProductById_404() throws Exception {

        Product product=null;
        when(productService.getProductById(1)).thenReturn(Optional.ofNullable(product));

        mockMvc.perform(get("/easy/product/{prdid}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    public void getProductAllProductsByBrand() throws Exception {

        when(productService.getAllProductsByBrand("zara")).thenReturn(getProductByBrand_zara());

        mockMvc.perform(get("/easy/product/brand/{brandName}","zara").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("getallproductbybrand/zara.json")));
    }

    @Test
    public void getProductAllProductsByBrand_404() throws Exception {

        when(productService.getAllProductsByBrand("zaraa")).thenReturn(getProductByBrand_zara());

        mockMvc.perform(get("/easy/product/brand/{brandName}","zara").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getAllProductsByCategory() throws Exception {

        when(productService.getAllProductsByCategory("shirt")).thenReturn(getProductByCategory_shirt());

        mockMvc.perform(get("/easy/product/category/{categoryName}","shirt").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(readResponse("productsbycategory/shirt.json")));
    }


    @Test
    public void getAllProductsByCategory_404() throws Exception {

        when(productService.getAllProductsByCategory("shirtt")).thenReturn(getProductByBrand_zara());

        mockMvc.perform(get("/easy/product/category/{categoryName}","shirt").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }




    @Test
    public void updateProduct() throws Exception{
        when(productService.updateProduct(any(Integer.class),any(Product.class))).thenReturn("DONE");

        mockMvc.perform(put("/easy/product/{productId}",1).content(readResponse("updateproduct/updateproduct_request.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }



    @Test
    public void updateProduct_404() throws Exception{
        when(productService.updateProduct(any(Integer.class),any(Product.class))).thenReturn("NotDone");

        mockMvc.perform(put("/easy/product/{productId}",1).content(readResponse("updateproduct/updateproduct_request.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    public void updateBrand() throws Exception{
        when(productService.updateBrand(any(Integer.class),any(ProductBrand.class))).thenReturn("DONE");

        mockMvc.perform(put("/easy/product/brand/{brandId}",1).content(readResponse("updatebrand/updatebrand.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }


    @Test
    public void updateBrand_404() throws Exception{
        when(productService.updateBrand(any(Integer.class),any(ProductBrand.class))).thenReturn("NotDone");

        mockMvc.perform(put("/easy/product/brand/{brandId}",1).content(readResponse("updatebrand/updatebrand.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }



    @Test
    public void updateCategory() throws Exception{
        when(productService.updateCategory(any(Integer.class),any(ProductCategory.class))).thenReturn("DONE");

        mockMvc.perform(put("/easy/product/category/{catId}",1).content(readResponse("updatecategory/updatecategoryrequest.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }


    @Test
    public void updateCategory_404() throws Exception{
        when(productService.updateCategory(any(Integer.class),any(ProductCategory.class))).thenReturn("NotDone");

        mockMvc.perform(put("/easy/product/category/{catId}",1).content(readResponse("updatecategory/updatecategoryrequest.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void insertProduct() throws Exception{
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(productService).insertProduct(readProduct("insertproduct/insertproduct.json"));

        mockMvc.perform(post("/easy/product/").content(readResponse("insertproduct/insertproduct.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }

    @Test
    public void insertBrand() throws Exception{
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(productService).insertBrand(readBrand("insertbrand/insertbrand.json"));

        mockMvc.perform(post("/easy/product/brand").content(readResponse("insertbrand/insertbrand.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }



    @Test
    public void insertCategory() throws Exception{
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(productService).insertCategory(readCategory("insertcategory/insertcategory.json"));

        mockMvc.perform(post("/easy/product/category").content(readResponse("insertcategory/insertcategory.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Updated")));
    }


    @Test
    public void deleteProduct() throws Exception{
        when(productService.deleteProduct(any(Integer.class))).thenReturn("DONE");
        mockMvc.perform(delete("/easy/product/{productId}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Deleted")));
    }


    @Test
    public void deleteAllProduct() throws Exception{
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(productService).deleteAllProducts();
        mockMvc.perform(delete("/easy/product/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Deleted")));
    }


    public String readResponse(String fileName) {
        try {
            String data = IOUtils.toString(this.getClass().getClassLoader()
                    .getResourceAsStream(fileName), "UTF-8");
            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Product readProduct(String fileName) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String data = IOUtils.toString(this.getClass().getClassLoader()
                    .getResourceAsStream(fileName), "UTF-8");

            //return objectMapper.convertValue(data,Product.class);

           Map<String,String> map= objectMapper.readValue(data,Map.class);

          return objectMapper.convertValue(map,Product.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ProductBrand readBrand(String fileName) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String data = IOUtils.toString(this.getClass().getClassLoader()
                    .getResourceAsStream(fileName), "UTF-8");

            //return objectMapper.convertValue(data,Product.class);

            Map<String,String> map= objectMapper.readValue(data,Map.class);

            return objectMapper.convertValue(map,ProductBrand.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public ProductCategory readCategory(String fileName) {
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String data = IOUtils.toString(this.getClass().getClassLoader()
                    .getResourceAsStream(fileName), "UTF-8");

            //return objectMapper.convertValue(data,Product.class);

            Map<String,String> map= objectMapper.readValue(data,Map.class);

            return objectMapper.convertValue(map,ProductCategory.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





    Page<Product> getAllProductsList() {
        JSONParser parser = new JSONParser();
        PageRequest pageRequest = new PageRequest(0, 20, Sort.unsorted());
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader("getAllProducts.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonarray = (JSONArray) obj.get("content");
        Product pd = null;
        List<Product> pdList = new ArrayList<Product>();
        for (int i = 0; i < jsonarray.size(); i++) {
            pd = new Product();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("id");
            pd.setId(l.intValue());
            pd.setPrice(((Long) jsonObject.get("price")).intValue());
            pd.setSex((String) jsonObject.get("sex"));
            pd.setUrl((String) jsonObject.get("url"));
            pd.setDescription((String) jsonObject.get("description"));
            pdList.add(pd);
        }

        PageImpl<Product> products = new PageImpl<Product>(pdList, pageRequest, 20);
        return (Page<Product>) products;
    }

    Page<ProductBrand> getAllBrandList() {
        JSONParser parser = new JSONParser();
        PageRequest pageRequest = new PageRequest(0, 20, Sort.unsorted());
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader("getAllBrands.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonarray = (JSONArray) obj.get("content");
        ProductBrand pb = null;
        List<ProductBrand> pbList = new ArrayList<ProductBrand>();
        for (int i = 0; i < jsonarray.size(); i++) {
            pb = new ProductBrand();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("id");
            pb.setId(l.intValue());
            pb.setBrandName(String.valueOf(jsonObject.get("brandName")));
            pbList.add(pb);
        }

        PageImpl<ProductBrand> products = new PageImpl<ProductBrand>(pbList, pageRequest, 5);
        return (Page<ProductBrand>) products;
    }


    Page<ProductCategory> getAllProductCategoryList() {
        JSONParser parser = new JSONParser();
        PageRequest pageRequest = new PageRequest(0, 20, Sort.unsorted());
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader("getAllProductCategory.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonarray = (JSONArray) obj.get("content");
        ProductCategory pc = null;
        List<ProductCategory> pcList = new ArrayList<ProductCategory>();

        for (int i = 0; i < jsonarray.size(); i++) {
            pc = new ProductCategory();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("id");
            pc.setId(l.intValue());
            pc.setCategoryName(String.valueOf(jsonObject.get("categoryName")));
            pcList.add(pc);
        }

        PageImpl<ProductCategory> products = new PageImpl<ProductCategory>(pcList, pageRequest, 4);
        return (Page<ProductCategory>) products;
    }


    Optional<Product> getProduct_1() {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(new FileReader("getAllProducts.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonarray = (JSONArray) obj.get("content");
        Product pd = new Product();
        JSONObject jsonObject = (JSONObject) jsonarray.get(0);
        Long l = (Long) jsonObject.get("id");
        pd.setId(l.intValue());
        pd.setPrice(((Long) jsonObject.get("price")).intValue());
        pd.setSex((String) jsonObject.get("sex"));
        pd.setUrl((String) jsonObject.get("url"));
        pd.setDescription((String) jsonObject.get("description"));
        return Optional.ofNullable(pd);
    }

    List<Product> getProductByBrand_zara(){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        JSONArray jsonarray=null;
        try {
            jsonarray = (JSONArray) parser.parse(new FileReader("zara.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Product pd = null;
        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < jsonarray.size(); i++) {
            pd = new Product();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("id");
            pd.setId(l.intValue());
            pd.setPrice(((Long) jsonObject.get("price")).intValue());
            pd.setSex((String) jsonObject.get("sex"));
            pd.setUrl((String) jsonObject.get("url"));
            pd.setDescription((String) jsonObject.get("description"));
            products.add(pd);
        }
        return products;
    }

    List<Product> getProductByCategory_shirt(){
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        JSONArray jsonarray=null;
        try {
            jsonarray = (JSONArray) parser.parse(new FileReader("shirt.json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Product pd = null;
        List<Product> products = new ArrayList<Product>();

        for (int i = 0; i < jsonarray.size(); i++) {
            pd = new Product();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("id");
            pd.setId(l.intValue());
            pd.setPrice(((Long) jsonObject.get("price")).intValue());
            pd.setSex((String) jsonObject.get("sex"));
            pd.setUrl((String) jsonObject.get("url"));
            pd.setDescription((String) jsonObject.get("description"));
            products.add(pd);
        }
        return products;
    }
}
