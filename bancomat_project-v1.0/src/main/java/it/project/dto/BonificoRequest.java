package it.project.dto;

@NoArgsConstructor // Costruttore senza argomenti per Jackson
@AllArgsConstructor 
public class BonificoRequest {

	 private String mittente;
	    private String destinatario;
	    private double importo;
		/**
		 * @return the mittente
		 */
		public String getMittente() {
			return mittente;
		}
		/**
		 * @return the destinatario
		 */
		public String getDestinatario() {
			return destinatario;
		}
		/**
		 * @return the importo
		 */
		public double getImporto() {
			return importo;
		}
		/**
		 * @param mittente the mittente to set
		 */
		public void setMittente(String mittente) {
			this.mittente = mittente;
		}
		/**
		 * @param destinatario the destinatario to set
		 */
		public void setDestinatario(String destinatario) {
			this.destinatario = destinatario;
		}
		/**
		 * @param importo the importo to set
		 */
		public void setImporto(double importo) {
			this.importo = importo;
		}
	    
	    
}
