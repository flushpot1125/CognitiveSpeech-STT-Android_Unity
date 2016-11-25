using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Test1 : MonoBehaviour {
public Text text;


public void bindService_Start(){
		Debug.Log("Unity test bindService Start");
		AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
		AndroidJavaObject currentUnityActivity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"); 
		AndroidJavaClass plugin = new AndroidJavaClass("com.microsoft.CognitiveServicesExample.NativeBridge");
		plugin.CallStatic("startBindService",currentUnityActivity);
}

public void bindService_Destroy(){
	Debug.Log("Unity test bindService Stop");
		AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer"); 
		AndroidJavaObject currentUnityActivity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity"); 
		AndroidJavaClass plugin = new AndroidJavaClass("com.microsoft.CognitiveServicesExample.NativeBridge");
		plugin.CallStatic("stopBindService",currentUnityActivity);

}

public void startRecognition(){
		AndroidJavaObject plugin = new AndroidJavaObject("com.microsoft.CognitiveServicesExample.NativeBridge");
		plugin.Call("startRecognition");
}

	public void onCallBackShowResult(string resultText){
		text.text = resultText;
	}

}
