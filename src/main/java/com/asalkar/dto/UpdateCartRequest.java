
package com.asalkar.dto;
public class UpdateCartRequest {
    private Long cartId;
    @Override
	public String toString() {
		return "UpdateCartRequest [cartId=" + cartId + ", volume=" + volume + "]";
	}
	private int volume;
	public Long getcartId() {
		return cartId;
	}
	public void setcartId(Long cartId) {
		this.cartId = cartId;
	}
	public int getvolume() {
		return volume;
	}
	public void setvolume(int volume) {
		this.volume = volume;
	}

    
    // Getters and setters
}
