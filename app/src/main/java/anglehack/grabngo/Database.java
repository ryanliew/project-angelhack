package anglehack.grabngo;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SLW2 on 6/4/2016.
 */
public class Database {
    //TAG for logs
    private final String TAG = "AHGrabNGo Database";
    //Base database pointer
    protected FirebaseDatabase firebaseInstance;
    protected DatabaseReference packageRef, userRef;

    //Data list
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Request> requests = new ArrayList<>();

    //Singleton implementation
    private static Database instance = new Database();

    private int lastUserId = 0; //placeholder of the last ID in user list
    private int lastRequestId = 0; //placeholder of the last ID in package list

    private Context context; //context that is calling this class

    private Database()
    {
        //To prevent people from creating a new instance of this class
    }

    public static Database getInstance(){ return instance; }

    //Initializing Database
    public void setContext( Context context )
    {
        firebaseInstance = FirebaseDatabase.getInstance();
        packageRef = firebaseInstance.getReference("Packages");
        userRef = firebaseInstance.getReference("User");
        this.context = context;
        packageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Request request = dataSnapshot.getValue(Request.class);
                requests.add(request);
                Log.d(TAG, dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Request request = dataSnapshot.getValue(Request.class);
                requests.set(Integer.parseInt(dataSnapshot.getKey()), request);
                Log.d(TAG, "Request "+ dataSnapshot.getKey() +" changed ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

            }
        });

        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                Log.d(TAG, dataSnapshot.getKey() );
                users.add(user);
                Log.d(TAG, "Added array size: " + users.size());
                lastUserId = Integer.parseInt( dataSnapshot.getKey() );
                Log.d(TAG, "Last User ID: " + lastUserId);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                users.set(Integer.parseInt(dataSnapshot.getKey()), user);
                Log.d(TAG, "User "+ dataSnapshot.getKey() +" changed ");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    //Common get and set functions
    public ArrayList<User> getUsers(){ return users; }
    public ArrayList<Request> getRequests() { return requests; }
    public int getLastUserId() { return lastUserId; }
    public int getLastPackageId() { return lastRequestId; }

    public void saveUser(User user)
    {
        //Username, Password, Name, IC, contactNum, Address, CarPlate, CarLicense, status, cartype
        //Creating dummy user
        //User user1 = new User( "username1", "password1", "testuser1", "testicnum1", "012-3456789", "test address 1", "testcarplate", "testcarlicense", 2 , 0);
        //userRef.child( String.format("%d", ++lastUserId) ).setValue( user1 );
        userRef.child( String.format("%d", ++lastUserId) ).setValue( user );
    }

    public void updateUser(User user)
    {
        Log.d(TAG, "Changed user:" + users.indexOf(user));
        userRef.child(String.format("%d", users.indexOf(user))).setValue(user);
    }

    public void saveRequest(Request request)
    {
        SimpleDateFormat ft = new SimpleDateFormat( Constants.dateFormat() );
        //acceptTime, requestTime, customerId, driverId, noOfItem, status, weight, price, origin, destination, paymentId, paymentStatus
        //Creating dummy request
        //Request request1 = new Request( ft.format(new Date()), ft.format(new Date()), 1, 2, 3, 0, 1, 25.00, "My house", "Your house", "testpaymentId", "testPaymentStatus");
        //packageRef.child( String.format("%d", ++lastRequestId) ).setValue( request1 );
        packageRef.child( String.format("%d", ++lastRequestId) ).setValue( request );
    }

    public void updateRequest(Request request)
    {
        Log.d(TAG, "Changed request: " + requests.indexOf(request) );
        packageRef.child(String.format("%d", requests.indexOf(request))).setValue(request);
    }

    public int getUserId(User user)
    {
        return users.indexOf(user);
    }


}
