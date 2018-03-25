package com.example.easyFashions;

import com.easy.model.*;
import com.easy.repository.*;
import com.easy.service.EasyProductService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EasyProductServiceTest {

    @Mock
    ProductRepository productRep;

    @Mock
    BrandRepository brandRep;

    @Mock
    CategoryRepository catRep;

    @Mock
    OfferRepository offerRep;

    @Mock
    UserRepository userRep;

    @Mock
    StoreRepository storeRep;

    @InjectMocks
    EasyProductService easyProductService;

    @Ignore
    @Test
    public void readUserjsonData() throws IOException, ParseException {
        when(userRep.saveAll(getUserList()));
        when(catRep.saveAll(getCategorylist()));
        when(offerRep.saveAll(getOfferList()));
        ProductBrand productBrand=new ProductBrand();
        ProductCategory productCategory=new ProductCategory();
        Offer offer=new Offer();
        when(brandRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(productBrand));
        when(catRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(productCategory));
        when(offerRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(offer));
        when(productRep.saveAll(getProductList()));
        when(brandRep.saveAll(getBrandData()));
        easyProductService.loadAllProducts();
    }

    @Test
    public void readCategoryjsonData() throws IOException, ParseException {
        when(catRep.saveAll(getCategorylist())).thenReturn(any());
        easyProductService.readCategoryjsonData();
    }

    @Test
    public void readBrandjsonData() throws IOException, ParseException {
        when(brandRep.saveAll(getBrandData())).thenReturn(any());
        easyProductService.readBrandjsonData();
    }

    @Test
    public void readOfferJsonData() throws IOException, ParseException {
        when(offerRep.saveAll(getOfferList())).thenReturn(any());
        easyProductService.readOfferJsonData();
    }


    @Test
    public void getAllProducts() throws IOException, ParseException {
        when(productRep.findAll(any(Pageable.class))).thenReturn(any());
        Pageable pageable=new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        easyProductService.getAllProducts(pageable);
    }


    @Test
    public void getAllBrands() throws IOException, ParseException {
        when(brandRep.findAll(any(Pageable.class))).thenReturn(any());
        Pageable pageable=new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        easyProductService.getAllBrands(pageable);
    }

    @Test
    public void getAllCategories() throws IOException, ParseException {
        when(catRep.findAll(any(Pageable.class))).thenReturn(any());
        Pageable pageable=new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        easyProductService.getAllCategories(pageable);
    }



    @Test
    public void getAllStores() throws IOException, ParseException {
        when(storeRep.findAll(any(Pageable.class))).thenReturn(any());
        Pageable pageable=new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        easyProductService.getAllStores(pageable);
    }


    @Ignore
    @Test
    public void readProductjsondata() throws IOException, ParseException {
        when(productRep.saveAll(getProductList())).thenReturn(any());
        ProductBrand productBrand=new ProductBrand();
        ProductCategory productCategory=new ProductCategory();
        Offer offer=new Offer();
        when(brandRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(productBrand));
        when(catRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(productCategory));
        when(offerRep.findById(any(Integer.class))).thenReturn(Optional.ofNullable(offer));
        easyProductService.readProductjsondata();
    }



    private List<User> getUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(3);
        user.setUserName("manoj");
        User user2 = new User();
        user2.setId(3);
        user2.setUserName("muni");
        User user3 = new User();
        user3.setId(3);
        user3.setUserName("ravisha");
        User user4 = new User();
        user4.setId(3);
        user4.setUserName("gowtham");
        users.add(user);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        return users;
    }


    private List<ProductCategory> getCategorylist() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("category.json"));

        JSONArray jsonarray = (JSONArray) obj;
        ProductCategory cd = null;
        List<ProductCategory> cdList = new ArrayList<ProductCategory>();
        for (int i = 0; i < jsonarray.size(); i++) {
            cd = new ProductCategory();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("ID");
            cd.setId(l.intValue());
            cd.setCategoryName(String.valueOf(jsonObject.get("CategoryName")));
            cdList.add(cd);

        }
        return cdList;
    }


    private List<Offer> getOfferList() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("offer.json"));
        JSONArray jsonarray = (JSONArray) obj;
        Offer offer = null;
        List<Offer> offList = new ArrayList<Offer>();
        for (int i = 0; i < jsonarray.size(); i++) {
            offer = new Offer();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("Id");
            offer.setOfferid(l.intValue());
            offer.setOfferCode(String.valueOf(jsonObject.get("OfferCode")));
            offer.setPercentOff(((Long) jsonObject.get("percentOff")).intValue());
            offer.setBundleSize(((Long) jsonObject.get("bundleSize")).intValue());
            offer.setReducedUnitPrice(((Long) jsonObject.get("reducedUnitPrice")).intValue());
            offer.setThresholdQuan(((Long) jsonObject.get("thresholdQuan")).intValue());
            offer.setPriorityOrder(((Long) jsonObject.get("priorityOrder")).intValue());
            offList.add(offer);

        }
        return offList;
    }

    private List<Product> getProductList() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("products.json"));

        JSONArray jsonarray = (JSONArray) obj;
        //            System.out.println(jsonarray);
        Product pd = null;
        List<Product> pdList = new ArrayList<Product>();
        for (int i = 0; i < jsonarray.size(); i++) {
            pd = new Product();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("ID");
            pd.setId(l.intValue());
            pd.setPrice(((Long) jsonObject.get("Price")).intValue());
            pd.setSex((String) jsonObject.get("Sex"));
            pd.setUrl((String) jsonObject.get("Url"));
            pd.setDescription((String) jsonObject.get("Description"));
            pd.setSex((String) jsonObject.get("Sex"));
            Long bdIdlong = (Long) jsonObject.get("BrandId");
        /*   *//* Optional<ProductBrand> pdbd = brandRep.findById(bdIdlong.intValue());
            pd.setBrand(pdbd.get());
            Long catId = (Long) jsonObject.get("CategoryId");
            Optional<ProductCategory> pdCd = catRep.findById(catId.intValue());
            pd.setCategory(pdCd.get());*//*
            JSONArray offArray = (JSONArray) jsonObject.get("offers");
            Set<Offer> offSet = new HashSet<Offer>();
            for (int j = 0; j < offArray.size(); j++) {
                Offer off = null;
                Long offerInt = (Long) offArray.get(j);
                if (offerInt != null && offerInt.intValue() > 0) {
                    off = offerRep.findById(offerInt.intValue()).get();
                    offSet.add(off);
                }
            }
            pd.setOffer(offSet);*/
            pdList.add(pd);

        }
        return pdList;
    }

    private List<ProductBrand> getBrandData() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("brand.json"));

        JSONArray jsonarray = (JSONArray) obj;
        ProductBrand bd = new ProductBrand();
        List<ProductBrand> bdList = new ArrayList<ProductBrand>();
        for (int i = 0; i < jsonarray.size(); i++) {
            bd = new ProductBrand();
            JSONObject jsonObject = (JSONObject) jsonarray.get(i);
            Long l = (Long) jsonObject.get("ID");
            bd.setId(l.intValue());
            bd.setBrandName(String.valueOf(jsonObject.get("BrandName")));
            bdList.add(bd);

        }
        brandRep.saveAll((Iterable<ProductBrand>) bdList);
      return  bdList;

    }


}
