import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.models.Reimbursements;
import com.revature.services.ReimbursementsService;

public class ReimbursementsTest {
	
	public static Reimbursements reimbursement = new Reimbursements();
	public static ReimbursementsService reimbService = new ReimbursementsService();
	
	@BeforeAll
	public static void ReimbursementsService() {
		System.out.println("In Before All");
		reimbService = new ReimbursementsService();
	}

	public void testGetAllReimbursements() {
		int result = reimbService.getAllReimbursements().size();
		assertEquals(result, 4);
	}
	
	public void testGetByEmployee() {
		int result = reimbService.getByEmployee(1).size();
		assertEquals(result, 3);
	}
}
