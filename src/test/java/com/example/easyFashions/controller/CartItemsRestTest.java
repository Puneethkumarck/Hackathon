package com.example.easyFashions.controller;
import com.easy.EasyFashionsApplication;
import com.easy.model.*;
import com.easy.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyFashionsApplication.class)
@AutoConfigureMockMvc
public class CartItemsRestTest {


    @MockBean
    CartService cartService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addtoCart() throws Exception{
        ObjectMapper objectMapper=new ObjectMapper();
        when(cartService.addToCart(any(Cart.class))).thenReturn((getAddToCart()));
        mockMvc.perform(post("/easy/product/cart").content(getAddToCart_Json())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("2")));
    }

    @Test
    public void deleteCart() throws Exception{

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return null;
            }
        }).when(cartService).deleteCart("punith");
        mockMvc.perform(delete("/easy/product/cart/{username}","punith")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Deleted")));
    }



    @Test
    public void getSavingsonCart() throws Exception {

        when(cartService.getSavingsOnCart(any(String.class))).thenReturn(500);
        mockMvc.perform(get("/easy/product/cart/savings/{userName}","punith").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"))
                .andExpect(content().string(equalTo("Savings-500")));

    }

    @Test
    public void getAllCartforUser() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
        when(cartService.getAllCartDetailsforUser(any(String.class))).thenReturn(getCartDetails());
        mockMvc.perform(get("/easy/product/cart/{userName}","punith").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                .andExpect(content().json(objectMapper.writeValueAsString(getCartDetails())));

    }



    private List<Cart> getCartDetails(){
        Cart cart=new Cart();
        User user=new User();
        user.setId(35);
        cart.setId(2);
        user.setUserName("punith");
        cart.setUser(user);
        cart.setPriceTotal(5000);
        cart.setSavings(500);
        CartItem cartItem=new CartItem();
        cartItem.setAmount(5000);
        cartItem.setId(50000L);
        cartItem.setProduct(new Product());
        cartItem.setQuantity(1);
        cartItem.setAmountRevised(0);
        Set<CartItem> cartItemSet=new HashSet<>();
        cartItemSet.add(cartItem);
        cart.setCartItems(cartItemSet);
        List<Cart> cartList=new ArrayList<>();
        cartList.add(cart);
        return cartList;
    }


    private Cart getAddToCart(){
        Cart cart=new Cart();
        User user=new User();
        user.setId(35);
        cart.setId(2);
        user.setUserName("punith");
        cart.setUser(user);
        cart.setPriceTotal(5000);
        cart.setSavings(500);
        CartItem cartItem=new CartItem();
        cartItem.setAmount(5000);
        cartItem.setId(50000L);
        cartItem.setProduct(new Product());
        cartItem.setQuantity(1);
        cartItem.setAmountRevised(0);
        Set<CartItem> cartItemSet=new HashSet<>();
       cartItemSet.add(cartItem);
        cart.setCartItems(cartItemSet);
        return cart;
    }


    private String getAddToCart_Json() throws JsonProcessingException {
       ObjectMapper objectMapper=new ObjectMapper();
        Cart cart=new Cart();
        User user=new User();
        user.setId(35);
        user.setUserName("punith");
        cart.setUser(user);
        cart.setPriceTotal(5000);
        cart.setSavings(500);
        CartItem cartItem=new CartItem();
        cartItem.setAmount(5000);
        cartItem.setId(50000L);
        cartItem.setProduct(new Product());
        cartItem.setQuantity(1);
        cartItem.setAmountRevised(0);
        Set<CartItem> cartItemSet=new HashSet<>();
        cart.setCartItems(cartItemSet);
        return objectMapper.writeValueAsString(cart);
    }
}
