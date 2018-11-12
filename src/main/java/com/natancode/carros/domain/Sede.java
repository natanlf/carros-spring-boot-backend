package com.natancode.carros.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Sede implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
	 	@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	 	private Integer id;
	 	private Double lat;
	 	private Double log;
	 	
	 	@OneToOne
		@JoinColumn(name="endereco_id")
		@MapsId //para o id do pagamento ser o mesmo do id do pedido
	 	private Endereco endereco;
	 	
	 	public Sede() {}

		public Sede(Integer id, Double x, Double y, Endereco endereco) {
			super();
			this.id = id;
			this.lat = x;
			this.log = y;
			this.endereco = endereco;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Double getX() {
			return lat;
		}

		public void setX(Double x) {
			this.lat = x;
		}

		public Double getY() {
			return log;
		}

		public void setY(Double y) {
			this.log = y;
		}

		public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Sede other = (Sede) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}
}
