import java.lang.reflect.InvocationTargetException

fun translateExecution(exception: Throwable?): Throwable? {
  val isInvocationTargetException = exception is InvocationTargetException

  if (isInvocationTargetException) {
    return (exception as InvocationTargetException).targetException
  }

  return exception
}

fun translateExecutionV2(exception: Throwable?) : Throwable? {
  val isInvocationTargetException = exception is InvocationTargetException

  if (isInvocationTargetException) {
    return exception.targetException
  }

  return exception
}
