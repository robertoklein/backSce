package br.com.pti.lassesce.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.pti.lassesce.model.LoanOrder;

@Repository
public interface LoanOrderRepository extends JpaRepository<LoanOrder, Long>{

	@Query("select l from LoanOrder l where "
			+ "(pullout >= :date1 and pullout <= :date2 and refound >= :date2 and returned = false)"
			+ "or (pullout <= :date1 and refound >= :date1 and refound <= :date2 and returned = false)"
			+ "or (pullout >= :date1 and refound <= :date2 and returned = false)"
			+ "or (pullout <= :date1 and refound <= :date1 and returned = false)"
			+ "or (pullout <= :date1 and refound >= :date2 and returned = false)")
List<LoanOrder> findByDateBetween(@Param("date1") LocalDate date1, @Param("date2") LocalDate date2); 
	
	// ==================== Serviços para adm ==================== 
	
	/**
	 * Query responsável por retornar as loans atrasadas
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByPulloutLessThanAndRefoundLessThanAndReturnedFalse(LocalDate dayDate, LocalDate dayDate2);
	
	/**
	 * Query responsável por retornar as loans a serem entregues no dia corrente.
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByRefoundEqualsAndDeliveredFalse(LocalDate dayDate);
	
	/**
	 * Query responsável por retornar as loans em aberto
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByPulloutLessThanEqualAndRefoundGreaterThanAndDeliveredTrue(LocalDate dayDate,LocalDate dayDate2);
	
	/**
	 * Query responsavel por retornar todas as loans que estao agendadas para datas futuras
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByPulloutGreaterThanEqualAndDeliveredFalse(LocalDate dayDate);
			
	/**
	 * Query responsável por retornar todas as loan que serão retiradas no dia corrente
	 * @param daydate
	 * @return
	 */
	List<LoanOrder> findByPulloutEqualsAndDeliveredFalse(LocalDate daydate);
	
	// ==================== Serviços para usr ==================== 
	
	/**
	 * Query responsável por retornar as loans atrasadas
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByUserIdAndPulloutLessThanAndRefoundLessThanAndReturnedFalse(long id, LocalDate dayDate, LocalDate dayDate2);
	
	/**
	 * Query responsável por retornar as loans a serem entregues no dia corrente.
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByUserIdAndRefoundEqualsAndDeliveredFalse(long id, LocalDate dayDate);
	
	/**
	 * Query responsável por retornar as loans em aberto
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByUserIdAndPulloutLessThanEqualAndRefoundGreaterThanAndDeliveredTrue(long id, LocalDate dayDate,LocalDate dayDate2);
	
	/**
	 * Query responsavel por retornar todas as loans que estao agendadas para datas futuras
	 * @param dayDate
	 * @return
	 */
	List<LoanOrder> findByUserIdAndPulloutGreaterThanEqualAndDeliveredFalse(long id, LocalDate dayDate);
			
	/**
	 * Query responsável por retornar todas as loan que serão retiradas no dia corrente
	 * @param daydate
	 * @return
	 */
	List<LoanOrder> findByUserIdAndPulloutEqualsAndDeliveredFalse(long id, LocalDate daydate);
}
      